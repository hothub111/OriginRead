package com.yuanyue.reader.data.parser

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TxtChapterParserTest {
    @Test
    fun `splits common chinese chapter headings`() {
        val text = buildString {
            appendLine("前言内容")
            appendLine("第一章 开端")
            appendLine("这里是第一章的正文内容")
            appendLine("第二章 转折")
            appendLine("这里是第二章的正文内容")
        }

        val chapters = TxtChapterParser.split(text)

        assertEquals(3, chapters.size)
        assertEquals("前言", chapters[0].title)
        assertEquals("第一章 开端", chapters[1].title)
        assertEquals("第二章 转折", chapters[2].title)
        assertEquals("这里是第二章的正文内容", chapters[2].content)
    }

    @Test
    fun `recognizes volume prefix and arabic numbers`() {
        val text = """
            正文 第一卷 第一章 少年
            内容一
            第001章 测试
            内容二
        """.trimIndent()

        val chapters = TxtChapterParser.split(text)

        assertEquals(2, chapters.size)
        assertTrue(chapters[0].title.contains("第一章"))
        assertEquals("第001章 测试", chapters[1].title)
    }

    @Test
    fun `large text without headings is split into safe chunks`() {
        val paragraph = "这是一段很长的正文内容，用来模拟没有章节标题的 TXT 文件。".repeat(40)
        val text = (1..300).joinToString("\n") { paragraph }

        val chapters = TxtChapterParser.split(text)

        assertTrue("大文件应该被切成多章，实际 ${chapters.size}", chapters.size > 1)
        assertTrue(
            "单章不能超过上限",
            chapters.all { it.content.length <= TxtChapterParser.MAX_CHAPTER_CHARS },
        )
        assertTrue(chapters.first().title.startsWith("正文"))
    }

    @Test
    fun `oversized chapter is split by paragraph boundary`() {
        val heading = "第一章 很长的一章"
        val body = (1..200).joinToString("\n") {
            "第${it}段：" + "文字".repeat(100)
        }
        val chapters = TxtChapterParser.split("$heading\n$body")

        assertTrue(chapters.size > 1)
        assertTrue(chapters.all { it.content.length <= TxtChapterParser.MAX_CHAPTER_CHARS })
        assertTrue(chapters.first().title.contains("1/"))
    }

    @Test
    fun `blank text returns empty list`() {
        assertTrue(TxtChapterParser.split("   \n\n  ").isEmpty())
    }
}
