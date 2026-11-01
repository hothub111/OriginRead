package com.yuanyue.reader.data

import com.yuanyue.reader.core.db.ComicChapterDao
import com.yuanyue.reader.core.db.ComicSourceDao
import com.yuanyue.reader.core.db.OnlineComicDao
import com.yuanyue.reader.model.ComicChapterEntity
import com.yuanyue.reader.model.ComicImageItem
import com.yuanyue.reader.model.ComicSourceEntity
import com.yuanyue.reader.model.OnlineComicEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class ComicRepository(
    private val sourceDao: ComicSourceDao,
    private val comicDao: OnlineComicDao,
    private val chapterDao: ComicChapterDao,
    private val ruleEngine: ComicSourceRuleEngine,
    private val client: OkHttpClient,
) {
    val sources: Flow<List<ComicSourceEntity>> = sourceDao.observeAll()

    fun comics(sourceId: Long): Flow<List<OnlineComicEntity>> =
        comicDao.observeBySource(sourceId)

    suspend fun addSource(input: String): OperationResult<Long> = withContext(Dispatchers.IO) {
        runCatching {
            val json = if (input.trim().startsWith("http://") || input.trim().startsWith("https://")) {
                fetchJson(input.trim())
            } else {
                input
            }
            val source = ComicSourceImporter.fromJson(json)
            sourceDao.insert(source)
        }.fold(
            onSuccess = { OperationResult.Success(it) },
            onFailure = { OperationResult.Error(it.message ?: "漫画源导入失败") },
        )
    }

    suspend fun deleteSource(source: ComicSourceEntity) = withContext(Dispatchers.IO) {
        val comics = comicDao.observeBySource(source.id).first()
        comics.forEach { comic -> chapterDao.deleteByComic(comic.id) }
        comicDao.deleteBySource(source.id)
        sourceDao.delete(source)
    }

    suspend fun search(
        sourceId: Long,
        keyword: String,
    ): OperationResult<List<OnlineComicEntity>> = withContext(Dispatchers.IO) {
        val source = sourceDao.getById(sourceId)
            ?: return@withContext OperationResult.Error("漫画源不存在")
        runCatching {
            val comics = ruleEngine.search(source, keyword)
            if (comics.isEmpty()) {
                emptyList()
            } else {
                val ids = comicDao.insertAll(comics)
                comics.mapIndexed { index, comic ->
                    val id = ids.getOrNull(index)?.takeIf { it > 0 } ?: comic.id
                    comic.copy(id = id)
                }
            }
        }.fold(
            onSuccess = { OperationResult.Success(it) },
            onFailure = { OperationResult.Error(it.message ?: "搜索失败") },
        )
    }

    suspend fun getComic(comicId: Long): OnlineComicEntity? = comicDao.getById(comicId)

    suspend fun detail(comic: OnlineComicEntity): OperationResult<OnlineComicEntity> =
        withContext(Dispatchers.IO) {
            val source = sourceDao.getById(comic.sourceId)
                ?: return@withContext OperationResult.Error("漫画源不存在")
            runCatching {
                val updated = ruleEngine.detail(source, comic)
                comicDao.insertAll(listOf(updated))
                updated
            }.fold(
                onSuccess = { OperationResult.Success(it) },
                onFailure = { OperationResult.Error(it.message ?: "详情获取失败") },
            )
        }

    suspend fun chapters(comic: OnlineComicEntity): OperationResult<List<ComicChapterEntity>> =
        withContext(Dispatchers.IO) {
            val source = sourceDao.getById(comic.sourceId)
                ?: return@withContext OperationResult.Error("漫画源不存在")
            runCatching {
                val chapters = ruleEngine.chapters(source, comic)
                if (chapters.isNotEmpty()) {
                    chapterDao.deleteByComic(comic.id)
                    chapterDao.insertAll(chapters)
                }
                // 重新查询拿到自增 id，否则目录 key 全是 0 会崩溃、点开章节也总是第一章。
                val persisted = chapterDao.getByComic(comic.id)
                if (persisted.isNotEmpty()) persisted else chapters
            }.fold(
                onSuccess = { OperationResult.Success(it) },
                onFailure = { OperationResult.Error(it.message ?: "目录获取失败") },
            )
        }

    suspend fun getChapters(comicId: Long): List<ComicChapterEntity> =
        chapterDao.getByComic(comicId)

    suspend fun images(
        comicId: Long,
        chapterId: Long,
    ): OperationResult<List<ComicImageItem>> = withContext(Dispatchers.IO) {
        val comic = comicDao.getById(comicId)
            ?: return@withContext OperationResult.Error("漫画不存在")
        val chapter = chapterDao.getById(chapterId)
            ?: return@withContext OperationResult.Error("章节不存在")
        val source = sourceDao.getById(comic.sourceId)
            ?: return@withContext OperationResult.Error("漫画源不存在")
        runCatching { ruleEngine.images(source, chapter.url) }.fold(
            onSuccess = { OperationResult.Success(it) },
            onFailure = { OperationResult.Error(it.message ?: "图片加载失败") },
        )
    }

    private fun fetchJson(url: String): String {
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "Mozilla/5.0 (Linux; Android 10) YuanyueReader/0.1")
            .build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IllegalStateException("漫画源下载失败：HTTP ${response.code}")
            return response.body?.string().orEmpty()
        }
    }
}
