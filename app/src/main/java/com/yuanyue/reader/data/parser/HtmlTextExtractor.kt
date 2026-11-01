package com.yuanyue.reader.data.parser

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.nodes.TextNode

object HtmlTextExtractor {
    private val blockTags = setOf(
        "p", "div", "h1", "h2", "h3", "h4", "h5", "h6",
        "li", "blockquote", "section", "article", "tr",
    )

    fun toText(html: String): String {
        if (html.isBlank()) return ""
        val document = Jsoup.parse(html)
        document.select("script,style,noscript,nav,footer,aside").remove()
        val body = document.body() ?: return ""
        return extract(body)
    }

    private fun extract(element: Element): String {
        val builder = StringBuilder()

        fun appendNode(node: org.jsoup.nodes.Node) {
            when (node) {
                is TextNode -> builder.append(node.text().trim())
                is Element -> {
                    val tag = node.tagName().lowercase()
                    if (tag == "br") {
                        builder.append('\n')
                        return
                    }
                    if (tag == "img") {
                        val alt = node.attr("alt")
                        if (alt.isNotBlank()) builder.append("[").append(alt).append("]")
                        return
                    }
                    val isBlock = tag in blockTags
                    if (isBlock) builder.append('\n')
                    node.childNodes().forEach { appendNode(it) }
                    if (isBlock) builder.append('\n')
                }
            }
        }

        element.childNodes().forEach { appendNode(it) }
        return builder.toString()
            .replace(Regex("[ \\t]+"), " ")
            .replace(Regex("\\n\\s*\\n+"), "\n\n")
            .trim()
    }
}
