package com.yuanyue.reader.data

import com.yuanyue.reader.core.db.RssArticleDao
import com.yuanyue.reader.core.db.RssFeedDao
import com.yuanyue.reader.model.RssArticleEntity
import com.yuanyue.reader.model.RssFeedEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class RssRepository(
    private val feedDao: RssFeedDao,
    private val articleDao: RssArticleDao,
    private val rssClient: RssClient,
) {
    val feeds: Flow<List<RssFeedEntity>> = feedDao.observeAll()
    val articles: Flow<List<RssArticleEntity>> = articleDao.observeAll()

    fun articlesForFeed(feedId: Long): Flow<List<RssArticleEntity>> =
        articleDao.observeByFeed(feedId)

    suspend fun getArticle(articleId: Long): RssArticleEntity? = articleDao.getById(articleId)

    suspend fun getFeed(feedId: Long): RssFeedEntity? = feedDao.getById(feedId)

    suspend fun addFeed(rawUrl: String): OperationResult<Long> {
        val url = normalizeUrl(rawUrl) ?: return OperationResult.Error("请输入合法的 RSS 地址")
        return withContext(Dispatchers.IO) {
            runCatching {
            val parsed = fetchAndParse(url)
            val feedId = feedDao.insert(
                RssFeedEntity(
                    title = parsed.title,
                    url = url,
                    description = parsed.description,
                    siteUrl = parsed.siteUrl,
                    faviconUrl = parsed.faviconUrl,
                ),
            )
            articleDao.insertAll(parsed.articles.map { it.copy(feedId = feedId) })
            feedId
            }.fold(
                onSuccess = { OperationResult.Success(it) },
                onFailure = { OperationResult.Error(it.message ?: "订阅源添加失败") },
            )
        }
    }

    suspend fun refreshFeed(feed: RssFeedEntity): OperationResult<Unit> {
        return withContext(Dispatchers.IO) {
            runCatching {
            val parsed = fetchAndParse(feed.url)
            articleDao.insertAll(parsed.articles.map { it.copy(feedId = feed.id) })
            }.fold(
                onSuccess = { OperationResult.Success(Unit) },
                onFailure = { OperationResult.Error(it.message ?: "刷新失败") },
            )
        }
    }

    suspend fun refreshAll() = withContext(Dispatchers.IO) {
        feedDao.observeAll().first().forEach { feed ->
            runCatching {
                val parsed = fetchAndParse(feed.url)
                articleDao.insertAll(parsed.articles.map { it.copy(feedId = feed.id) })
            }
        }
    }

    suspend fun deleteFeed(feed: RssFeedEntity) {
        withContext(Dispatchers.IO) {
            articleDao.deleteByFeed(feed.id)
            feedDao.delete(feed)
        }
    }

    suspend fun markRead(articleId: Long, read: Boolean = true) {
        articleDao.updateRead(articleId, read)
    }

    suspend fun toggleFavorite(article: RssArticleEntity) {
        articleDao.updateFavorite(article.id, !article.isFavorite)
    }

    private suspend fun fetchAndParse(url: String): ParsedFeed {
        val xml = rssClient.get(url)
        return RssParser().parse(xml, url)
    }

    private fun normalizeUrl(rawUrl: String): String? {
        val trimmed = rawUrl.trim()
        if (trimmed.isEmpty()) return null
        return if (trimmed.startsWith("http://") || trimmed.startsWith("https://")) {
            trimmed
        } else {
            "https://$trimmed"
        }
    }
}
