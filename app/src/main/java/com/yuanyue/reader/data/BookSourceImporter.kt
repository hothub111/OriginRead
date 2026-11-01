package com.yuanyue.reader.data

import com.yuanyue.reader.model.BookSourceEntity
import org.json.JSONObject

/**
 * 书源 JSON 解析。
 *
 * 同时兼容两种常见格式：
 * - 本应用早期的扁平字段（bookSourceName / bookList / chapterList / content ...）；
 * - Legado「阅读 3.0」的嵌套字段（ruleSearch / ruleBookInfo / ruleToc / ruleContent）。
 * 之前只认扁平字段，粘贴标准书源 JSON 时会报「缺少书源名称」，看起来像 App 出错。
 */
object BookSourceImporter {
    fun fromJson(json: String): BookSourceEntity {
        val cleaned = json.trim().removePrefix("\uFEFF")
        if (cleaned.startsWith("[")) {
            val count = runCatching { org.json.JSONArray(cleaned).length() }.getOrDefault(0)
            throw IllegalArgumentException(
                if (count > 1) {
                    "检测到 $count 个书源，请一次粘贴一个书源后再导入"
                } else {
                    "书源 JSON 格式不正确：请粘贴单个书源对象"
                },
            )
        }
        val root = runCatching { JSONObject(cleaned) }.getOrElse {
            throw IllegalArgumentException("书源 JSON 解析失败：${it.message ?: "格式不正确"}")
        }
        val search = root.optJSONObject("ruleSearch")
        val info = root.optJSONObject("ruleBookInfo")
        val toc = root.optJSONObject("ruleToc")
        val content = root.optJSONObject("ruleContent")

        val name = firstString(root, "bookSourceName", "name")
            ?: throw IllegalArgumentException("书源格式不完整：缺少书源名称（bookSourceName）")
        val url = firstString(root, "bookSourceUrl", "url")
            ?: throw IllegalArgumentException("书源格式不完整：缺少书源地址（bookSourceUrl）")

        return BookSourceEntity(
            name = name,
            url = url,
            searchUrl = firstString(root, "searchUrl"),
            searchListRule = firstString(search, "bookList")
                ?: firstString(root, "bookList", "searchList"),
            bookNameRule = firstString(search, "name")
                ?: firstString(root, "bookName", "nameRule"),
            bookAuthorRule = firstString(search, "author")
                ?: firstString(root, "bookAuthor", "author"),
            bookCoverRule = firstString(search, "coverUrl", "cover")
                ?: firstString(root, "bookCover", "cover"),
            bookUrlRule = firstString(search, "bookUrl")
                ?: firstString(root, "bookUrl", "detailUrl"),
            introRule = firstString(info, "intro") ?: firstString(root, "intro"),
            coverRule = firstString(info, "coverUrl", "cover") ?: firstString(root, "cover"),
            tocUrlRule = firstString(info, "tocUrl") ?: firstString(root, "tocUrl"),
            chapterListRule = firstString(toc, "chapterList")
                ?: firstString(root, "chapterList"),
            chapterNameRule = firstString(toc, "chapterName")
                ?: firstString(root, "chapterName"),
            chapterUrlRule = firstString(toc, "chapterUrl")
                ?: firstString(root, "chapterUrl"),
            contentRule = firstString(content, "content") ?: firstString(root, "content"),
            contentReplaceRules = firstString(content, "replaceRegex")
                ?: firstString(root, "contentReplace"),
        )
    }

    /** 返回书源缺少的必要字段（用于添加前的结构检查）。 */
    fun missingFields(source: BookSourceEntity): List<String> {
        val missing = mutableListOf<String>()
        if (source.searchUrl.isNullOrBlank()) missing += "搜索地址 searchUrl"
        if (source.searchListRule.isNullOrBlank()) missing += "搜索结果列表 ruleSearch.bookList"
        if (source.bookUrlRule.isNullOrBlank()) missing += "详情链接 ruleSearch.bookUrl"
        if (source.chapterListRule.isNullOrBlank()) missing += "章节目录 ruleToc.chapterList"
        if (source.chapterUrlRule.isNullOrBlank()) missing += "章节链接 ruleToc.chapterUrl"
        if (source.contentRule.isNullOrBlank()) missing += "正文规则 ruleContent.content"
        return missing
    }

    fun toJson(source: BookSourceEntity): String {
        val root = JSONObject().apply {
            put("bookSourceName", source.name)
            put("bookSourceUrl", source.url)
            put("searchUrl", source.searchUrl ?: "")
            put("bookList", source.searchListRule ?: "")
            put("bookName", source.bookNameRule ?: "")
            put("bookAuthor", source.bookAuthorRule ?: "")
            put("bookCover", source.bookCoverRule ?: "")
            put("bookUrl", source.bookUrlRule ?: "")
            put("intro", source.introRule ?: "")
            put("tocUrl", source.tocUrlRule ?: "")
            put("chapterList", source.chapterListRule ?: "")
            put("chapterName", source.chapterNameRule ?: "")
            put("chapterUrl", source.chapterUrlRule ?: "")
            put("content", source.contentRule ?: "")
            source.contentReplaceRules?.let { put("contentReplace", it) }
        }
        return root.toString(2)
    }

    private fun firstString(root: JSONObject?, vararg keys: String): String? {
        if (root == null) return null
        keys.forEach { key ->
            val value = root.optString(key)
            if (value.isNotBlank()) return value
        }
        return null
    }
}
