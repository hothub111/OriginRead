package com.yuanyue.reader.data.parser

import com.yuanyue.reader.model.BookChapter

/**
 * TXT 分章。
 *
 * 旧实现只识别很窄的标题格式，遇到「第一卷 第一章」或没有章节标题的大文件时，
 * 会把整本书塞进一个章节，阅读页在主线程上排版几 MB 文本，直接卡死/ANR。
 * 这里补充了标题识别，并把过大的章节按段落边界切分，保证单章长度可控。
 */
object TxtChapterParser {
    const val MAX_CHAPTER_CHARS = 12_000

    private val chapterPattern = Regex(
        "^\\s*(?:" +
            "(?:正文[卷部]?\\s*)?" +
            "第\\s*[0-9０-９一二三四五六七八九十百千万零〇两]+\\s*[章卷节回集部篇话]" +
            "(?:\\s.*|[:：、.．\\-—].*)?|" +
            "(?:序章|楔子|引子|前言|序言|后记|尾声|终章|番外)" +
            "(?:[\\s:：、.．\\-—].*|[0-9一二三四五六七八九十]+)?|" +
            "(?:chapter|CHAPTER)\\s+[0-9０-９]+.*" +
            ")\\s*$",
        RegexOption.IGNORE_CASE,
    )

    fun split(text: String, maxChars: Int = MAX_CHAPTER_CHARS): List<BookChapter> {
        if (text.isBlank()) return emptyList()

        val chapters = mutableListOf<BookChapter>()
        val buffer = StringBuilder()
        var currentTitle = "前言"
        var hasHeading = false

        fun flush() {
            if (buffer.isNotBlank()) {
                chapters += BookChapter(currentTitle, buffer.toString().trim())
            }
            buffer.clear()
        }

        text.lineSequence().forEach { rawLine ->
            val line = rawLine.trim()
            if (line.isNotEmpty() && isHeading(line)) {
                flush()
                currentTitle = line
                hasHeading = true
            } else {
                if (buffer.isNotEmpty()) buffer.append('\n')
                buffer.append(rawLine)
            }
        }
        flush()

        if (chapters.isEmpty()) return emptyList()

        val titled = if (!hasHeading && chapters.size == 1) {
            listOf(BookChapter("正文", chapters.first().content))
        } else {
            chapters
        }

        return titled
            .filter { it.content.isNotBlank() }
            .flatMap { splitOversized(it, maxChars) }
    }

    private fun isHeading(line: String): Boolean {
        if (line.length > 60) return false
        return chapterPattern.matches(line)
    }

    private fun splitOversized(chapter: BookChapter, maxChars: Int): List<BookChapter> {
        if (chapter.content.length <= maxChars) return listOf(chapter)
        val parts = chunkByParagraph(chapter.content, maxChars)
        if (parts.size <= 1) return listOf(chapter)
        return parts.mapIndexed { index, part ->
            BookChapter(
                title = "${chapter.title}（${index + 1}/${parts.size}）",
                content = part,
            )
        }
    }

    private fun chunkByParagraph(content: String, maxChars: Int): List<String> {
        val parts = mutableListOf<String>()
        val builder = StringBuilder()

        fun flush() {
            if (builder.isNotBlank()) parts += builder.toString()
            builder.clear()
        }

        content.split('\n').forEach { paragraph ->
            if (paragraph.length > maxChars) {
                flush()
                var start = 0
                while (start < paragraph.length) {
                    val end = (start + maxChars).coerceAtMost(paragraph.length)
                    parts += paragraph.substring(start, end)
                    start = end
                }
                return@forEach
            }
            if (builder.isNotEmpty() && builder.length + 1 + paragraph.length > maxChars) {
                flush()
            }
            if (builder.isNotEmpty()) builder.append('\n')
            builder.append(paragraph)
        }
        flush()
        return parts
    }
}
