package com.yuanyue.reader.data

import com.yuanyue.reader.model.RssArticleEntity
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xml.sax.InputSource
import java.io.StringReader
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.xml.parsers.DocumentBuilderFactory

data class ParsedFeed(
    val title: String,
    val description: String? = null,
    val siteUrl: String? = null,
    val faviconUrl: String? = null,
    val articles: List<RssArticleEntity>,
)

class RssParser {
    fun parse(xml: String, feedUrl: String): ParsedFeed {
        val factory = DocumentBuilderFactory.newInstance().apply {
            isNamespaceAware = true
            runCatching {
                setFeature("http://xml.org/sax/features/external-general-entities", false)
            }
            runCatching {
                setFeature("http://xml.org/sax/features/external-parameter-entities", false)
            }
        }
        val document = factory.newDocumentBuilder()
            .parse(InputSource(StringReader(xml)))
        val root = document.documentElement
        return when ((root.localName ?: root.nodeName).lowercase()) {
            "rss" -> parseRss(root, feedUrl)
            "feed" -> parseAtom(root, feedUrl)
            else -> throw IllegalArgumentException("暂不支持该订阅格式")
        }
    }

    private fun parseRss(root: Element, feedUrl: String): ParsedFeed {
        val channel = firstChild(root, "channel") ?: root
        val title = childText(channel, "title").orEmpty().ifBlank { feedUrl }
        val description = childText(channel, "description").orEmpty().takeIf { it.isNotBlank() }
        val siteUrl = childText(channel, "link").orEmpty().takeIf { it.isNotBlank() }
        val faviconUrl = firstChild(channel, "image")?.let { childText(it, "url") }
        val articles = children(channel, "item").mapNotNull { item ->
            val itemTitle = childText(item, "title").orEmpty().trim()
            val link = childText(item, "link").orEmpty().trim().takeIf { it.isNotBlank() }
            val guid = childText(item, "guid").orEmpty().trim()
                .ifBlank { link ?: itemTitle }
                .ifBlank { "${feedUrl}#${System.currentTimeMillis()}" }
            RssArticleEntity(
                feedId = 0,
                guid = guid,
                title = itemTitle.ifBlank { "无标题" },
                link = link,
                description = childText(item, "description")?.trim(),
                contentHtml = childText(item, "encoded")?.trim(),
                author = childText(item, "creator")?.trim(),
                publishedAt = parseDate(childText(item, "pubDate")),
            )
        }
        return ParsedFeed(
            title = title,
            description = description,
            siteUrl = siteUrl,
            faviconUrl = faviconUrl,
            articles = articles,
        )
    }

    private fun parseAtom(root: Element, feedUrl: String): ParsedFeed {
        val title = childText(root, "title").orEmpty().ifBlank { feedUrl }
        val description = childText(root, "subtitle").orEmpty().takeIf { it.isNotBlank() }
        val siteUrl = firstChild(root, "link")?.getAttribute("href")?.takeIf { it.isNotBlank() }
        val faviconUrl = childText(root, "icon") ?: childText(root, "logo")
        val articles = children(root, "entry").mapNotNull { entry ->
            val entryTitle = childText(entry, "title").orEmpty().trim()
            val link = children(entry, "link").firstOrNull { it.getAttribute("rel") == "alternate" }
                ?.getAttribute("href")?.takeIf { it.isNotBlank() }
                ?: children(entry, "link").firstOrNull()?.getAttribute("href")?.takeIf { it.isNotBlank() }
            val guid = childText(entry, "id").orEmpty().trim()
                .ifBlank { link ?: entryTitle }
                .ifBlank { "${feedUrl}#${System.currentTimeMillis()}" }
            RssArticleEntity(
                feedId = 0,
                guid = guid,
                title = entryTitle.ifBlank { "无标题" },
                link = link,
                description = childText(entry, "summary")?.trim(),
                contentHtml = childText(entry, "content")?.trim(),
                author = firstChild(entry, "author")?.let { childText(it, "name")?.trim() },
                publishedAt = parseDate(childText(entry, "updated")),
            )
        }
        return ParsedFeed(
            title = title,
            description = description,
            siteUrl = siteUrl,
            faviconUrl = faviconUrl,
            articles = articles,
        )
    }

    private fun childText(parent: Element, localName: String): String? {
        return firstChild(parent, localName)?.textContent?.trim()?.takeIf { it.isNotBlank() }
    }

    private fun firstChild(parent: Element, localName: String): Element? {
        return children(parent, localName).firstOrNull()
    }

    private fun children(parent: Element, localName: String): List<Element> {
        val result = mutableListOf<Element>()
        val nodes = parent.childNodes
        for (i in 0 until nodes.length) {
            val node = nodes.item(i)
            if (node is Element && (node.localName == localName || node.nodeName == localName)) {
                result.add(node)
            }
        }
        return result
    }

    private fun parseDate(value: String?): Long? {
        if (value.isNullOrBlank()) return null
        val formatters = listOf(
            DateTimeFormatter.RFC_1123_DATE_TIME,
            DateTimeFormatter.ISO_OFFSET_DATE_TIME,
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
        )
        return formatters.firstNotNullOfOrNull { formatter ->
            runCatching {
                if (formatter == DateTimeFormatter.ISO_LOCAL_DATE_TIME) {
                    java.time.LocalDateTime.parse(value, formatter)
                        .toInstant(ZoneOffset.UTC)
                } else {
                    OffsetDateTime.parse(value, formatter).toInstant()
                }
            }.getOrNull()
        }?.toEpochMilli() ?: runCatching { Instant.parse(value).toEpochMilli() }.getOrNull()
    }
}
