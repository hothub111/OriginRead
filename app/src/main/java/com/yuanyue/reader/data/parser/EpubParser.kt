package com.yuanyue.reader.data.parser

import com.yuanyue.reader.model.BookChapter
import org.jsoup.Jsoup
import org.w3c.dom.Element
import org.xml.sax.InputSource
import java.io.File
import java.io.FileOutputStream
import java.io.StringReader
import java.util.zip.ZipFile
import javax.xml.parsers.DocumentBuilderFactory

data class ParsedEpub(
    val title: String,
    val author: String?,
    val coverPath: String?,
    val chapters: List<BookChapter>,
)

class EpubParser {
    fun parse(file: File, coverOutputDir: File): ParsedEpub {
        ZipFile(file).use { zip ->
            val opfPath = findOpfPath(zip)
            val opfEntry = zip.getEntry(opfPath)
                ?: throw IllegalArgumentException("EPUB 缺少 OPF 文件")
            val opfXml = zip.getInputStream(opfEntry).bufferedReader(Charsets.UTF_8).use { it.readText() }
            val document = DocumentBuilderFactory.newInstance().apply {
                isNamespaceAware = true
            }.newDocumentBuilder().parse(InputSource(StringReader(opfXml)))
            val root = document.documentElement

            val title = firstText(root, "title") ?: file.nameWithoutExtension
            val author = firstText(root, "creator")
            val baseDir = opfPath.substringBeforeLast('/', "").let { if (it.isEmpty()) "" else "$it/" }

            val manifest = mutableMapOf<String, String>()
            val coverCandidate = mutableMapOf<String, String>()
            val itemNodes = root.getElementsByTagNameNS("*", "item")
            for (i in 0 until itemNodes.length) {
                val item = itemNodes.item(i) as? Element ?: continue
                val id = item.getAttribute("id")
                val href = item.getAttribute("href")
                if (id.isNotBlank() && href.isNotBlank()) {
                    manifest[id] = href
                    val properties = item.getAttribute("properties")
                    val mediaType = item.getAttribute("media-type")
                    if (properties.contains("cover-image") ||
                        id.equals("cover", ignoreCase = true) ||
                        mediaType.startsWith("image/")
                    ) {
                        coverCandidate[id] = href
                    }
                }
            }

            val chapters = mutableListOf<BookChapter>()
            val spineNodes = root.getElementsByTagNameNS("*", "itemref")
            var chapterIndex = 0
            for (i in 0 until spineNodes.length) {
                val itemRef = spineNodes.item(i) as? Element ?: continue
                val idref = itemRef.getAttribute("idref")
                val href = manifest[idref] ?: continue
                val entryPath = resolveHref(baseDir, href)
                val entry = zip.getEntry(entryPath) ?: continue
                val html = zip.getInputStream(entry).bufferedReader(Charsets.UTF_8).use { it.readText() }
                val documentHtml = Jsoup.parse(html)
                val heading = documentHtml.selectFirst("h1, h2, h3")?.text().orEmpty().trim()
                val documentTitle = documentHtml.title().trim()
                val chapterTitle = heading.ifBlank {
                    if (documentTitle in setOf("未知", "无标题", "Untitled", "unknown")) {
                        ""
                    } else {
                        documentTitle
                    }
                }.ifBlank { "第 ${chapterIndex + 1} 章" }
                val content = documentHtml.body()
                    ?.let { extractText(it) }
                    .orEmpty()
                if (content.isNotBlank()) {
                    chapters.add(BookChapter(chapterTitle, content))
                    chapterIndex++
                }
            }
            if (chapters.isEmpty()) {
                chapters.add(BookChapter(title, "该 EPUB 未解析出正文内容"))
            }

            val coverPath = extractCover(zip, baseDir, coverCandidate, coverOutputDir)
            return ParsedEpub(
                title = title.ifBlank { file.nameWithoutExtension },
                author = author,
                coverPath = coverPath,
                chapters = chapters,
            )
        }
    }

    private fun findOpfPath(zip: ZipFile): String {
        val containerEntry = zip.getEntry("META-INF/container.xml")
            ?: throw IllegalArgumentException("EPUB 缺少 container.xml")
        val xml = zip.getInputStream(containerEntry).bufferedReader(Charsets.UTF_8).use { it.readText() }
        val document = DocumentBuilderFactory.newInstance().apply {
            isNamespaceAware = true
        }.newDocumentBuilder().parse(InputSource(StringReader(xml)))
        val rootfiles = document.getElementsByTagNameNS("*", "rootfile")
        for (i in 0 until rootfiles.length) {
            val rootfile = rootfiles.item(i) as? Element ?: continue
            val path = rootfile.getAttribute("full-path")
            if (path.isNotBlank()) return path
        }
        throw IllegalArgumentException("EPUB 中未找到 OPF 路径")
    }

    private fun extractCover(
        zip: ZipFile,
        baseDir: String,
        candidates: Map<String, String>,
        outputDir: File,
    ): String? {
        val href = candidates["cover"] ?: candidates.values.firstOrNull { value ->
            value.substringAfterLast('.').lowercase() in setOf("jpg", "jpeg", "png", "webp")
        } ?: candidates.values.firstOrNull()
        if (href == null) return null
        val entryPath = resolveHref(baseDir, href)
        val entry = zip.getEntry(entryPath) ?: return null
        val extension = href.substringAfterLast('.', "jpg").lowercase()
        val outFile = File(outputDir, "cover_${System.currentTimeMillis()}.$extension")
        outputDir.mkdirs()
        zip.getInputStream(entry).use { input ->
            FileOutputStream(outFile).use { output -> input.copyTo(output) }
        }
        return outFile.absolutePath
    }

    private fun firstText(root: Element, localName: String): String? {
        val nodes = root.getElementsByTagNameNS("*", localName)
        for (i in 0 until nodes.length) {
            val text = nodes.item(i).textContent?.trim()
            if (!text.isNullOrBlank()) return text
        }
        return null
    }

    private fun extractText(element: org.jsoup.nodes.Element): String {
        val blockTags = setOf(
            "p", "div", "h1", "h2", "h3", "h4", "h5", "h6",
            "li", "blockquote", "section", "article", "tr",
        )
        val builder = StringBuilder()

        fun appendNode(node: org.jsoup.nodes.Node) {
            when (node) {
                is org.jsoup.nodes.TextNode -> {
                    builder.append(node.text().trim())
                }

                is org.jsoup.nodes.Element -> {
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

    private fun resolveHref(baseDir: String, href: String): String {
        val combined = when {
            href.startsWith("/") -> href.drop(1)
            baseDir.isEmpty() -> href
            else -> baseDir + href
        }
        val parts = combined.split('/')
        val stack = ArrayDeque<String>()
        parts.forEach { part ->
            when (part) {
                "", "." -> Unit
                ".." -> if (stack.isNotEmpty()) stack.removeLast()
                else -> stack.addLast(part)
            }
        }
        return stack.joinToString("/")
    }
}
