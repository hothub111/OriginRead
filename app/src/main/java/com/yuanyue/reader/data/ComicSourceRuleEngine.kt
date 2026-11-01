package com.yuanyue.reader.data

import com.yuanyue.reader.model.ComicChapterEntity
import com.yuanyue.reader.model.ComicImageItem
import com.yuanyue.reader.model.ComicSourceEntity
import com.yuanyue.reader.model.OnlineComicEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.net.URI
import java.net.URLEncoder

class ComicSourceRuleEngine(
    private val client: OkHttpClient,
) {
    suspend fun search(
        source: ComicSourceEntity,
        keyword: String,
        page: Int = 1,
    ): List<OnlineComicEntity> = withContext(Dispatchers.IO) {
        val searchUrl = source.searchUrl
            ?: throw IllegalStateException("该漫画源未配置搜索地址")
        val url = renderUrl(searchUrl, mapOf("key" to keyword, "page" to page.toString()))
        val html = fetch(url)
        val document = Jsoup.parse(html, url)
        val items = document.select(source.searchListRule ?: "a")
        items.mapNotNull { item ->
            val title = extract(item, source.comicTitleRule)?.trim().orEmpty()
                .ifBlank { item.text().trim() }
            val comicUrl = extract(item, source.comicUrlRule)
                ?.let { resolveUrl(url, it) }
                ?: resolveUrl(url, item.attr("href"))
            if (title.isBlank() || !isHttpUrl(comicUrl)) return@mapNotNull null
            OnlineComicEntity(
                sourceId = source.id,
                title = title,
                coverUrl = extract(item, source.comicCoverRule)?.let { resolveUrl(url, it) },
                comicUrl = comicUrl,
            )
        }.distinctBy { it.comicUrl }
    }

    suspend fun detail(
        source: ComicSourceEntity,
        comic: OnlineComicEntity,
    ): OnlineComicEntity = withContext(Dispatchers.IO) {
        val html = fetch(comic.comicUrl)
        val document = Jsoup.parse(html, comic.comicUrl)
        comic.copy(
            title = extract(document, source.comicTitleRule)?.trim()?.ifBlank { comic.title } ?: comic.title,
            coverUrl = extract(document, source.comicCoverRule)
                ?.let { resolveUrl(comic.comicUrl, it) }
                ?: comic.coverUrl,
            intro = extract(document, "article, .intro, #intro")?.trim() ?: comic.intro,
        )
    }

    suspend fun chapters(
        source: ComicSourceEntity,
        comic: OnlineComicEntity,
    ): List<ComicChapterEntity> = withContext(Dispatchers.IO) {
        val html = fetch(comic.comicUrl)
        val document = Jsoup.parse(html, comic.comicUrl)
        val items = document.select(source.chapterListRule ?: "a")
        items.mapIndexedNotNull { index, item ->
            val title = extract(item, source.chapterNameRule)?.trim().orEmpty()
                .ifBlank { item.text().trim() }
            val url = extract(item, source.chapterUrlRule)
                ?.let { resolveUrl(comic.comicUrl, it) }
                ?: resolveUrl(comic.comicUrl, item.attr("href"))
            if (title.isBlank() || !isHttpUrl(url)) return@mapIndexedNotNull null
            ComicChapterEntity(
                comicId = comic.id,
                sourceId = source.id,
                title = title,
                url = url,
                chapterIndex = index,
            )
        }
    }

    suspend fun images(
        source: ComicSourceEntity,
        chapterUrl: String,
    ): List<ComicImageItem> = withContext(Dispatchers.IO) {
        val html = fetch(chapterUrl)
        val document = Jsoup.parse(html, chapterUrl)
        val items = document.select(source.imageListRule ?: "img")
        items.mapNotNull { image ->
            val rawUrl = extract(image, source.imageUrlRule)
                ?: image.attr("data-src").ifBlank { image.attr("src") }
            val url = if (rawUrl.isBlank()) {
                ""
            } else {
                resolveUrl(chapterUrl, rawUrl)
            }
            if (!isHttpUrl(url)) return@mapNotNull null
            ComicImageItem(url = url, referer = chapterUrl)
        }.distinctBy { it.url }
    }

    private fun renderUrl(template: String, replacements: Map<String, String>): String {
        var result = template
        replacements.forEach { (key, value) ->
            val encoded = URLEncoder.encode(value, "UTF-8")
            result = result
                .replace("{{$key}}", encoded)
                .replace("{{${key.uppercase()}}}", encoded)
        }
        return result
    }

    private fun fetch(url: String): String {
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "Mozilla/5.0 (Linux; Android 10) YuanyueReader/0.1")
            .header("Referer", url)
            .build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IllegalStateException("请求失败：HTTP ${response.code}")
            return response.body?.string().orEmpty()
        }
    }

    private fun extract(element: Element, rule: String?): String? {
        if (rule.isNullOrBlank()) return null
        val selector = rule.substringBeforeLast('@', rule)
        val attribute = if (rule.contains('@')) rule.substringAfterLast('@') else null
        val target = element.selectFirst(selector) ?: return null
        return attribute?.let { target.attr(it) } ?: target.text()
    }

    private fun resolveUrl(base: String, value: String): String {
        if (isHttpUrl(value)) return value
        return runCatching { URI(base).resolve(value).toString() }.getOrDefault(value)
    }

    private fun isHttpUrl(value: String): Boolean =
        value.startsWith("http://") || value.startsWith("https://")
}
