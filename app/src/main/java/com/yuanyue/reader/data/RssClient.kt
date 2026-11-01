package com.yuanyue.reader.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

class RssClient {
    val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(25, TimeUnit.SECONDS)
        .followRedirects(true)
        .followSslRedirects(true)
        .build()

    suspend fun get(url: String, vararg headers: Pair<String, String>): String =
        withContext(Dispatchers.IO) {
            val builder = Request.Builder()
                .url(url)
                .header("User-Agent", "YuanyueReader/0.1 (Android)")
            headers.forEach { (name, value) -> builder.header(name, value) }
            client.newCall(builder.build()).execute().use { response ->
                if (!response.isSuccessful) {
                    throw IllegalStateException("请求失败：HTTP ${response.code}")
                }
                response.body?.string().orEmpty()
            }
        }
}
