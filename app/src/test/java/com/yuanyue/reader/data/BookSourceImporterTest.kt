package com.yuanyue.reader.data

import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class BookSourceImporterTest {
    @Test
    fun `parses legacy flat source json`() {
        val json = """
            {
              "bookSourceName": "测试源",
              "bookSourceUrl": "https://example.com",
              "searchUrl": "https://example.com/search?q={{key}}",
              "bookList": ".book-item",
              "bookName": ".title@text",
              "bookUrl": "a@href",
              "chapterList": ".chapters a",
              "chapterUrl": "a@href",
              "content": "#content"
            }
        """.trimIndent()

        val source = BookSourceImporter.fromJson(json)

        assertEquals("测试源", source.name)
        assertEquals("https://example.com", source.url)
        assertEquals(".book-item", source.searchListRule)
        assertEquals(".title@text", source.bookNameRule)
        assertEquals("a@href", source.bookUrlRule)
        assertEquals("#content", source.contentRule)
        assertTrue(BookSourceImporter.missingFields(source).isEmpty())
    }

    @Test
    fun `parses legado nested source json`() {
        val json = """
            {
              "bookSourceName": "阅读源",
              "bookSourceUrl": "https://novel.example.com",
              "searchUrl": "https://novel.example.com/search?key={{key}}",
              "ruleSearch": {
                "bookList": "class.book-list@tag.li",
                "name": "tag.a@text",
                "author": "class.author@text",
                "bookUrl": "tag.a@href",
                "coverUrl": "tag.img@src"
              },
              "ruleBookInfo": {
                "intro": "class.intro@text",
                "coverUrl": "class.cover@src"
              },
              "ruleToc": {
                "chapterList": "class.chapter-list@tag.a",
                "chapterName": "text",
                "chapterUrl": "href"
              },
              "ruleContent": {
                "content": "id.content"
              }
            }
        """.trimIndent()

        val source = BookSourceImporter.fromJson(json)

        assertEquals("阅读源", source.name)
        assertEquals("class.book-list@tag.li", source.searchListRule)
        assertEquals("tag.a@text", source.bookNameRule)
        assertEquals("tag.a@href", source.bookUrlRule)
        assertEquals("class.chapter-list@tag.a", source.chapterListRule)
        assertEquals("id.content", source.contentRule)
        assertEquals("class.intro@text", source.introRule)
        assertTrue(BookSourceImporter.missingFields(source).isEmpty())
    }

    @Test
    fun `rejects arrays with a clear message`() {
        val error = runCatching {
            BookSourceImporter.fromJson("""[{"bookSourceName":"a"},{"bookSourceName":"b"}]""")
        }.exceptionOrNull()

        assertNotNull(error)
        assertTrue(error!!.message.orEmpty().contains("一次粘贴一个"))
    }

    @Test
    fun `reports missing required rules`() {
        val source = BookSourceImporter.fromJson("""{"bookSourceName":"a","bookSourceUrl":"https://b"}""")
        val missing = BookSourceImporter.missingFields(source)

        assertTrue(missing.any { it.contains("searchUrl") })
        assertTrue(missing.any { it.contains("chapterList") })
        assertTrue(missing.any { it.contains("content") })
    }

    @Test
    fun `to json round trips basic fields`() {
        val json = """{"bookSourceName":"a","bookSourceUrl":"https://b","content":"#c"}"""
        val source = BookSourceImporter.fromJson(json)
        val parsed = JSONObject(BookSourceImporter.toJson(source))

        assertEquals("a", parsed.optString("bookSourceName"))
        assertEquals("#c", parsed.optString("content"))
    }
}
