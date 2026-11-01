package com.yuanyue.reader.data

import com.yuanyue.reader.data.parser.HtmlTextExtractor
import com.yuanyue.reader.model.BookSourceEntity
import com.yuanyue.reader.model.OnlineBookEntity
import com.yuanyue.reader.model.OnlineChapterEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.json.JSONArray
import org.json.JSONObject
import java.net.URI
import java.net.URLEncoder
import java.util.concurrent.TimeUnit

data class BookSourceTestResult(
    val ok: Boolean,
    val message: String,
    val warning: String? = null,
    val sampleCount: Int = 0,
)

/**
 * 书源规则引擎。
 *
 * 规则兼容：
 * - 普通 CSS 选择器 / `a@href` 形式；
 * - Legado 的 `class.xxx@tag.a@href`、`tag.a.0@href`、`text`、`@text` 形式；
 * - `||` 备用规则。
 */
class BookSourceRuleEngine(
    private val client: OkHttpClient,
) {
    /** 添加书源前先跑一遍：结构检查 + 实际搜索 + 目录 + 正文抽样。 */
    suspend fun testSource(source: BookSourceEntity, keyword: String = "我"): BookSourceTestResult =
        withContext(Dispatchers.IO) {
            val missing = BookSourceImporter.missingFields(source)
            if (missing.isNotEmpty()) {
                return@withContext BookSourceTestResult(
                    ok = false,
                    message = "书源规则不完整，缺少：${missing.joinToString("、")}",
                )
            }

            var lastError: String? = null
            val keywords = listOf(keyword, "斗罗大陆").distinct()
            for (sample in keywords) {
                try {
                    val books = search(source, sample, page = 1)
                    if (books.isEmpty()) {
                        lastError = "搜索「$sample」没有返回结果"
                        continue
                    }
                    val first = books.first()
                    val chapterList = chapters(source, first)
                    if (chapterList.isEmpty()) {
                        return@withContext BookSourceTestResult(
                            ok = false,
                            message = "书源能搜索，但目录规则解析不到章节，可能已失效或规则不匹配",
                            sampleCount = books.size,
                        )
                    }
                    val contentLength = runCatching {
                        content(source, chapterList.first().url).length
                    }.getOrDefault(0)
                    val warning = if (contentLength < 20) {
                        "目录可用，但正文规则没有取到内容，阅读时可能失败"
                    } else {
                        null
                    }
                    return@withContext BookSourceTestResult(
                        ok = true,
                        message = "书源可用：搜索到 ${books.size} 条结果，目录 ${chapterList.size} 章",
                        warning = warning,
                        sampleCount = books.size,
                    )
                } catch (error: Exception) {
                    lastError = error.message ?: "请求失败"
                }
            }

            BookSourceTestResult(
                ok = false,
                message = "书源不可用：${lastError ?: "无法访问书源站点"}",
            )
        }

    suspend fun search(
        source: BookSourceEntity,
        keyword: String,
        page: Int = 1,
    ): List<OnlineBookEntity> = withContext(Dispatchers.IO) {
        val searchUrl = source.searchUrl
            ?: throw IllegalStateException("该书源未配置搜索地址")
        val url = renderUrl(
            template = searchUrl,
            replacements = mapOf(
                "key" to keyword,
                "searchKey" to keyword,
                "page" to page.toString(),
            ),
        )
        val html = fetch(url, source.url)
        val document = Jsoup.parse(html, url)
        val items = document.select(source.searchListRule ?: "a")
        items.mapNotNull { item ->
            val title = extract(item, source.bookNameRule)
                ?.trim()
                .orEmpty()
                .ifBlank { item.text().trim() }
            val bookUrl = extract(item, source.bookUrlRule)
                ?.let { resolveUrl(url, it) }
                ?: resolveUrl(url, item.attr("href"))
            if (title.isBlank() || !isHttpUrl(bookUrl)) return@mapNotNull null
            OnlineBookEntity(
                sourceId = source.id,
                title = title,
                author = extract(item, source.bookAuthorRule)?.trim(),
                coverUrl = extract(item, source.bookCoverRule)?.let { resolveUrl(url, it) },
                bookUrl = bookUrl,
                intro = null,
                latestChapter = null,
            )
        }.distinctBy { it.bookUrl }
    }

    suspend fun detail(
        source: BookSourceEntity,
        book: OnlineBookEntity,
    ): OnlineBookEntity = withContext(Dispatchers.IO) {
        val html = fetch(book.bookUrl, source.url)
        val document = Jsoup.parse(html, book.bookUrl)
        book.copy(
            title = extract(document, source.bookNameRule)?.trim()?.ifBlank { book.title } ?: book.title,
            author = extract(document, source.bookAuthorRule)?.trim() ?: book.author,
            coverUrl = extract(document, source.coverRule)
                ?.let { resolveUrl(book.bookUrl, it) }
                ?: book.coverUrl,
            intro = extract(document, source.introRule)?.trim() ?: book.intro,
        )
    }

    suspend fun chapters(
        source: BookSourceEntity,
        book: OnlineBookEntity,
    ): List<OnlineChapterEntity> = withContext(Dispatchers.IO) {
        val tocUrl = source.tocUrlRule
            ?.let {
                renderUrl(
                    template = it,
                    replacements = mapOf("bookUrl" to book.bookUrl),
                )
            }
            ?: book.bookUrl
        val resolvedTocUrl = resolveUrl(book.bookUrl, tocUrl)
        val html = fetch(resolvedTocUrl, book.bookUrl)
        val document = Jsoup.parse(html, resolvedTocUrl)
        val items = document.select(source.chapterListRule ?: "a")
        items.mapIndexedNotNull { index, item ->
            val title = extract(item, source.chapterNameRule)
                ?.trim()
                .orEmpty()
                .ifBlank { item.text().trim() }
            val url = extract(item, source.chapterUrlRule)
                ?.let { resolveUrl(resolvedTocUrl, it) }
                ?: resolveUrl(resolvedTocUrl, item.attr("href"))
            if (title.isBlank() || !isHttpUrl(url)) return@mapIndexedNotNull null
            OnlineChapterEntity(
                bookId = book.id,
                sourceId = source.id,
                title = title,
                url = url,
                chapterIndex = index,
            )
        }
            .distinctBy { it.url }
            .mapIndexed { index, chapter -> chapter.copy(chapterIndex = index) }
    }

    suspend fun content(
        source: BookSourceEntity,
        chapterUrl: String,
    ): String = withContext(Dispatchers.IO) {
        val html = fetch(chapterUrl, source.url)
        val document = Jsoup.parse(html, chapterUrl)
        val element = selectContentElement(document, source.contentRule)
            ?: document.body()
            ?: document
        val text = HtmlTextExtractor.toText(element.html())
        applyReplaceRules(text, source.contentReplaceRules)
    }

    private fun selectContentElement(document: Document, rule: String?): Element? {
        if (rule.isNullOrBlank()) return null
        val alternatives = rule.split("||").map { it.trim() }.filter { it.isNotEmpty() }
        for (alternative in alternatives) {
            val (selector, _) = parseRule(alternative)
            if (selector.isBlank()) continue
            runCatching { document.selectFirst(selector) }.getOrNull()?.let { return it }
        }
        // Legado 的规则经常包含 @text / @html 后缀，去掉后再试一次
        val (selector, _) = parseRule(rule.substringBeforeLast('@'))
        return runCatching { document.selectFirst(selector) }.getOrNull()
    }

    private fun renderUrl(
        template: String,
        replacements: Map<String, String>,
    ): String {
        var result = template
        replacements.forEach { (key, value) ->
            val encoded = URLEncoder.encode(value, "UTF-8")
            result = result
                .replace("{{$key}}", encoded)
                .replace("{{${key.uppercase()}}}", encoded)
                .replace("{{ ${key} }}", encoded)
        }
        // Legado 的 POST 参数后缀：,{"method":"POST",...}
        val optionIndex = result.indexOf(",{")
        if (optionIndex > 0 && result.substring(optionIndex).trimStart().startsWith(",{")) {
            result = result.substring(0, optionIndex)
        }
        return result
    }

    private fun fetch(url: String, referer: String? = null): String {
        val builder = Request.Builder()
            .url(url)
            .header(
                "User-Agent",
                "Mozilla/5.0 (Linux; Android 13) AppleWebKit/537.36 " +
                    "(KHTML, like Gecko) Chrome/124.0.0.0 Mobile Safari/537.36",
            )
            .header(
                "Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
            )
            .header("Accept-Language", "zh-CN,zh;q=0.9")
        referer?.let { builder.header("Referer", it) }
        val call = client.newCall(builder.build())
        call.timeout().timeout(20, TimeUnit.SECONDS)
        call.execute().use { response ->
            if (!response.isSuccessful) {
                throw IllegalStateException("请求失败：HTTP ${response.code}")
            }
            return response.body?.string().orEmpty()
        }
    }

    private fun extract(element: Element, rule: String?): String? {
        if (rule.isNullOrBlank()) return null
        val alternatives = rule.split("||").map { it.trim() }.filter { it.isNotEmpty() }
        for (alternative in alternatives) {
            val (selector, attribute) = parseRule(alternative)
            val target = if (selector.isBlank()) {
                element
            } else {
                runCatching { element.selectFirst(selector) }.getOrNull() ?: continue
            }
            val value = when (attribute) {
                null -> target.text()
                "html" -> target.html()
                else -> target.attr(attribute)
            }
            if (value.isNotBlank()) return value
        }
        return null
    }

    /**
     * 把 Legado 规则翻译成 CSS 选择器 + 属性名。
     * 例：`class.book-item@tag.a@href` -> (".book-item a", "href")
     *     `@css:.content p@text` -> (".content p", null)
     */
    private fun parseRule(rawRule: String): Pair<String, String?> {
        var rule = rawRule.trim().removePrefix("@css:").trim()
        if (rule.isEmpty()) return "" to null
        if (rule == "text" || rule == "@text") return "" to null

        val parts = rule.split('@').map { it.trim() }.filter { it.isNotEmpty() }
        if (parts.isEmpty()) return "" to null

        val last = parts.last()
        val lastIsText = last == "text"
        val lastIsHtml = last == "html"
        val lastIsAttribute = !lastIsText && !lastIsHtml && isAttribute(last)

        val selectorParts = when {
            lastIsText || lastIsHtml || lastIsAttribute -> parts.dropLast(1)
            else -> parts
        }
        val selector = selectorParts
            .joinToString(" ") { translateSelector(it) }
            .trim()
        val attribute = when {
            lastIsText -> null
            lastIsHtml -> "html"
            lastIsAttribute -> last
            else -> null
        }
        return selector to attribute
    }

    private fun translateSelector(segment: String): String {
        val value = segment.trim()
        if (value.isEmpty()) return ""
        return when {
            value.startsWith("class.") ->
                "." + value.removePrefix("class.").trim().replace(" ", ".")

            value.startsWith("id.") ->
                "#" + value.removePrefix("id.").trim().replace(" ", "#")

            value.startsWith("tag.") -> {
                // tag.a.0 / tag.span.1 -> a / span
                value.removePrefix("tag.").split('.').firstOrNull().orEmpty()
            }

            else -> value
        }
    }

    private fun isAttribute(value: String): Boolean {
        if (value.startsWith("data-")) return true
        return value in setOf(
            "href", "src", "title", "alt", "content", "value", "style",
        )
    }

    private fun resolveUrl(base: String, value: String): String {
        if (isHttpUrl(value)) return value
        if (value.isBlank()) return base
        return runCatching { URI(base).resolve(value).toString() }.getOrDefault(value)
    }

    private fun isHttpUrl(value: String): Boolean =
        value.startsWith("http://") || value.startsWith("https://")

    private fun applyReplaceRules(text: String, json: String?): String {
        if (json.isNullOrBlank()) return text
        val array = runCatching { JSONArray(json) }.getOrNull() ?: return text
        var result = text
        for (i in 0 until array.length()) {
            when (val item = array.opt(i)) {
                is JSONObject -> {
                    val pattern = item.optString("pattern").ifBlank {
                        item.optString("rule")
                    }
                    val replacement = item.optString("replacement").ifBlank {
                        item.optString("to")
                    }
                    if (pattern.isNotBlank()) {
                        result = runCatching {
                            Regex(pattern).replace(result, replacement)
                        }.getOrDefault(result)
                    }
                }

                is String -> {
                    result = runCatching {
                        Regex(item).replace(result, "")
                    }.getOrDefault(result)
                }
            }
        }
        return result
    }
}
