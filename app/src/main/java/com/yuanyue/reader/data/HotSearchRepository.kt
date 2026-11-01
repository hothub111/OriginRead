package com.yuanyue.reader.data

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.net.SocketTimeoutException
import java.net.URLEncoder
import java.net.UnknownHostException

enum class HotSearchPlatform(val displayName: String) {
    WEIBO("微博"),
    BAIDU("百度"),
    ZHIHU("知乎"),
    BILIBILI("哔哩哔哩"),
}

data class HotSearchItem(
    val title: String,
    val url: String,
    val heat: String? = null,
)

data class HotSearchResult(
    val items: List<HotSearchItem>,
    val fromCache: Boolean = false,
    val warning: String? = null,
    val cachedAt: Long? = null,
)

/**
 * 热榜抓取。
 *
 * 各平台接口在风控时经常返回 200 + 空数据 / HTML 登录页，之前会直接得到一个空列表，
 * 界面因此出现“白屏”。这里统一做三件事：
 * 1. 每个平台准备备用接口，主接口失败时自动切换；
 * 2. 空数据视为失败，不再当成“没有热搜”；
 * 3. 失败时回退到上次成功缓存，并附上提示，保证界面始终有内容或明确错误。
 */
class HotSearchRepository(
    private val client: OkHttpClient,
    private val context: Context? = null,
) {
    suspend fun fetch(platform: HotSearchPlatform): HotSearchResult = withContext(Dispatchers.IO) {
        val cached = readCache(platform)
        try {
            val items = when (platform) {
                HotSearchPlatform.WEIBO -> fetchWeibo()
                HotSearchPlatform.BAIDU -> fetchBaidu()
                HotSearchPlatform.ZHIHU -> fetchZhihu()
                HotSearchPlatform.BILIBILI -> fetchBilibili()
            }
            if (items.isEmpty()) {
                throw IllegalStateException("接口返回空数据")
            }
            writeCache(platform, items)
            HotSearchResult(items = items)
        } catch (error: Exception) {
            if (cached != null && cached.items.isNotEmpty()) {
                HotSearchResult(
                    items = cached.items,
                    fromCache = true,
                    warning = "网络受限，已展示上次缓存的热榜（${formatTime(cached.time)}）",
                    cachedAt = cached.time,
                )
            } else {
                throw IllegalStateException(friendlyError(platform, error), error)
            }
        }
    }

    private fun fetchWeibo(): List<HotSearchItem> {
        val body = get(
            "https://weibo.com/ajax/side/hotSearch",
            "https://weibo.com/",
        )
        val data = runCatching { JSONObject(body).optJSONObject("data") }.getOrNull()
        val realtime = data?.optJSONArray("realtime")
        val primary = realtime?.let { array ->
            parseArray(array) { item ->
                val title = item.optString("word").ifBlank { item.optString("note") }
                val fallback = weiboSearchUrl(title)
                val scheme = item.optString("word_scheme")
                    .let { if (isHttpUrl(it)) it else fallback }
                HotSearchItem(title, scheme, item.optString("num").takeIf { it.isNotBlank() })
            }
        }.orEmpty()
        if (primary.isNotEmpty()) return primary
        return fetchWeiboMobile()
    }

    private fun fetchWeiboMobile(): List<HotSearchItem> {
        val body = get(
            "https://m.weibo.cn/api/container/getIndex?containerid=106003type%3D25%26t%3D3%26disable_hot%3D1%26filter_type%3Drealtimehot",
            "https://m.weibo.cn/",
        )
        val cards = runCatching {
            JSONObject(body).optJSONObject("data")?.optJSONArray("cards")
        }.getOrNull() ?: return emptyList()

        val result = mutableListOf<HotSearchItem>()
        for (i in 0 until cards.length()) {
            val group = cards.optJSONObject(i)?.optJSONArray("card_group") ?: continue
            result += parseArray(group) { item ->
                val title = item.optString("desc").ifBlank { item.optString("word") }
                val fallback = weiboSearchUrl(title)
                val scheme = item.optString("scheme")
                    .let { if (isHttpUrl(it)) it else fallback }
                HotSearchItem(title, scheme, item.optString("desc_extr").takeIf { it.isNotBlank() })
            }
            if (result.isNotEmpty()) break
        }
        return result
    }

    private fun fetchBaidu(): List<HotSearchItem> {
        val urls = listOf(
            "https://top.baidu.com/api/board?platform=wise&tab=realtime",
            "https://top.baidu.com/api/board?platform=pc&tab=realtime",
        )
        for (url in urls) {
            val body = runCatching { get(url) }.getOrNull() ?: continue
            val items = parseBaidu(body)
            if (items.isNotEmpty()) return items
        }
        return emptyList()
    }

    private fun parseBaidu(body: String): List<HotSearchItem> {
        val data = runCatching { JSONObject(body).optJSONObject("data") }.getOrNull()
            ?: return emptyList()
        val cards = data.optJSONArray("cards") ?: return emptyList()
        val objects = mutableListOf<JSONObject>()
        collectWordObjects(cards, objects)
        return objects.mapNotNull { item ->
            val title = item.optString("word")
            if (title.isBlank()) return@mapNotNull null
            HotSearchItem(
                title = title,
                url = item.optString("url")
                    .let { if (isHttpUrl(it)) it else baiduSearchUrl(title) },
                heat = item.optString("hotScore").ifBlank {
                    item.optString("hotTag").takeIf { it != "0" } ?: ""
                }.takeIf { it.isNotBlank() },
            )
        }.distinctBy { it.title }
    }

    /** 百度接口把榜单放在 cards[].content[].content[] 这类多层数组里，这里递归取出带 word 的对象。 */
    private fun collectWordObjects(node: Any?, out: MutableList<JSONObject>) {
        when (node) {
            is JSONArray -> {
                for (i in 0 until node.length()) {
                    collectWordObjects(node.opt(i), out)
                }
            }

            is JSONObject -> {
                if (node.optString("word").isNotBlank()) {
                    out += node
                    return
                }
                val keys = node.keys()
                while (keys.hasNext()) {
                    collectWordObjects(node.opt(keys.next()), out)
                }
            }
        }
    }

    private fun fetchZhihu(): List<HotSearchItem> {
        val body = get(
            "https://www.zhihu.com/api/v3/feed/topstory/hot-lists/total?limit=20&desktop=true",
            "https://www.zhihu.com/hot",
        )
        val array = JSONObject(body).optJSONArray("data") ?: return emptyList()
        return parseArray(array) { item ->
            val target = item.optJSONObject("target") ?: JSONObject()
            val title = target.optString("title")
            val id = target.optString("id")
            val url = target.optString("url")
                .let { if (isHttpUrl(it)) it else zhihuFallbackUrl(id, title) }
            HotSearchItem(title, url, item.optString("detail_text").takeIf { it.isNotBlank() })
        }
    }

    private fun fetchBilibili(): List<HotSearchItem> {
        val body = runCatching {
            get(
                "https://api.bilibili.com/x/web-interface/ranking/v2?rid=0&type=all",
                "https://www.bilibili.com/",
            )
        }.getOrElse {
            get(
                "https://api.bilibili.com/x/web-interface/popular?ps=20&pn=1",
                "https://www.bilibili.com/",
            )
        }
        val list = JSONObject(body).optJSONObject("data")?.optJSONArray("list") ?: return emptyList()
        return parseArray(list) { item ->
            val title = item.optString("title")
            val bvid = item.optString("bvid")
            val url = item.optString("short_link_v2")
                .let { if (isHttpUrl(it)) it else "https://www.bilibili.com/video/$bvid" }
            val view = item.optJSONObject("stat")?.optLong("view")?.let { formatNumber(it) }
            HotSearchItem(title, url, view)
        }
    }

    private fun parseArray(
        array: JSONArray,
        transform: (JSONObject) -> HotSearchItem,
    ): List<HotSearchItem> {
        val result = mutableListOf<HotSearchItem>()
        for (i in 0 until array.length()) {
            val item = array.optJSONObject(i) ?: continue
            val hot = runCatching { transform(item) }.getOrNull() ?: continue
            if (hot.title.isNotBlank()) result.add(hot)
        }
        return result
    }

    private fun get(url: String, referer: String? = null): String {
        val builder = Request.Builder()
            .url(url)
            .header(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                    "(KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36",
            )
            .header("Accept", "application/json, text/plain, */*")
            .header("Accept-Language", "zh-CN,zh;q=0.9")
        referer?.let { builder.header("Referer", it) }
        client.newCall(builder.build()).execute().use { response ->
            if (!response.isSuccessful) {
                val hint = when (response.code) {
                    403, 418, 412 -> "，可能触发访问限制"
                    404 -> "，接口地址可能已变更"
                    else -> ""
                }
                throw IllegalStateException("HTTP ${response.code}$hint")
            }
            val body = response.body?.string().orEmpty()
            if (body.isBlank()) throw IllegalStateException("接口返回空内容")
            if (body.trimStart().startsWith("<")) {
                throw IllegalStateException("接口返回了网页而不是数据，可能触发访问限制")
            }
            return body
        }
    }

    private fun friendlyError(platform: HotSearchPlatform, error: Throwable): String {
        val reason = when (error) {
            is UnknownHostException -> "网络不可用"
            is SocketTimeoutException -> "请求超时"
            else -> error.message ?: "未知错误"
        }
        return "${platform.displayName}热榜暂时不可用：$reason。可稍后重试或切换其他平台。"
    }

    private fun formatNumber(value: Long): String = when {
        value >= 100_000_000 -> "%.1f亿".format(value / 100_000_000.0)
        value >= 10_000 -> "%.1f万".format(value / 10_000.0)
        else -> value.toString()
    }

    private fun isHttpUrl(url: String): Boolean =
        url.startsWith("http://") || url.startsWith("https://")

    private fun weiboSearchUrl(title: String): String =
        "https://s.weibo.com/weibo?q=${URLEncoder.encode(title, "UTF-8")}"

    private fun baiduSearchUrl(title: String): String =
        "https://www.baidu.com/s?wd=${URLEncoder.encode(title, "UTF-8")}"

    private fun zhihuFallbackUrl(id: String, title: String): String =
        if (id.isNotBlank()) {
            "https://www.zhihu.com/question/$id"
        } else {
            "https://www.zhihu.com/search?type=content&q=${URLEncoder.encode(title, "UTF-8")}"
        }

    private fun prefs() = context?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private fun readCache(platform: HotSearchPlatform): CachedHotSearch? {
        val raw = prefs()?.getString(cacheKey(platform), null) ?: return null
        return runCatching {
            val root = JSONObject(raw)
            val array = root.optJSONArray("items") ?: JSONArray()
            val items = buildList {
                for (i in 0 until array.length()) {
                    val item = array.optJSONObject(i) ?: continue
                    val title = item.optString("title")
                    if (title.isBlank()) continue
                    add(
                        HotSearchItem(
                            title = title,
                            url = item.optString("url"),
                            heat = item.optString("heat").takeIf { it.isNotBlank() },
                        ),
                    )
                }
            }
            if (items.isEmpty()) null else CachedHotSearch(items, root.optLong("time"))
        }.getOrNull()
    }

    private fun writeCache(platform: HotSearchPlatform, items: List<HotSearchItem>) {
        val array = JSONArray()
        items.forEach { item ->
            array.put(
                JSONObject().apply {
                    put("title", item.title)
                    put("url", item.url)
                    item.heat?.let { put("heat", it) }
                },
            )
        }
        val root = JSONObject().apply {
            put("time", System.currentTimeMillis())
            put("items", array)
        }
        prefs()?.edit()?.putString(cacheKey(platform), root.toString())?.apply()
    }

    private fun cacheKey(platform: HotSearchPlatform) = "hot_${platform.name}"

    private fun formatTime(time: Long): String = runCatching {
        java.text.SimpleDateFormat("MM-dd HH:mm", java.util.Locale.CHINA)
            .format(java.util.Date(time))
    }.getOrDefault("")

    private data class CachedHotSearch(
        val items: List<HotSearchItem>,
        val time: Long,
    )

    private companion object {
        const val PREFS_NAME = "hot_search_cache"
    }
}
