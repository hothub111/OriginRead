package com.yuanyue.reader.data

import com.yuanyue.reader.data.parser.HtmlTextExtractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

data class ExtractedArticle(
    val title: String,
    val content: String,
)

class ArticleContentRepository(private val client: OkHttpClient) {
    suspend fun extract(url: String, fallbackTitle: String): OperationResult<ExtractedArticle> =
        withContext(Dispatchers.IO) {
            runCatching {
                require(url.startsWith("http://") || url.startsWith("https://")) {
                    "该链接暂不可读取"
                }
                val request = Request.Builder()
                    .url(url)
                    .header(
                        "User-Agent",
                        "Mozilla/5.0 (Linux; Android 10) YuanyueReader/0.1",
                    )
                    .build()
                val html = client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        throw IllegalStateException("请求失败：HTTP ${response.code}")
                    }
                    response.body?.string().orEmpty()
                }
                val text = HtmlTextExtractor.toText(html)
                if (text.isBlank()) throw IllegalStateException("未提取到正文")
                ExtractedArticle(
                    title = fallbackTitle.ifBlank { "未命名文章" },
                    content = text,
                )
            }.fold(
                onSuccess = { OperationResult.Success(it) },
                onFailure = { OperationResult.Error(it.message ?: "正文提取失败") },
            )
        }
}
