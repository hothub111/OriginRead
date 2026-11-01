package com.yuanyue.reader.data

import com.yuanyue.reader.core.db.BookSourceDao
import com.yuanyue.reader.core.db.CachedContentDao
import com.yuanyue.reader.core.db.OnlineBookDao
import com.yuanyue.reader.core.db.OnlineChapterDao
import com.yuanyue.reader.model.BookSourceEntity
import com.yuanyue.reader.model.CachedChapterContentEntity
import com.yuanyue.reader.model.OnlineBookEntity
import com.yuanyue.reader.model.OnlineChapterEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

sealed interface BookSourceAddResult {
    data class Added(
        val id: Long,
        val message: String,
        val warning: String? = null,
    ) : BookSourceAddResult

    /** 结构或网络检测未通过，用户可以确认后强制添加。 */
    data class Unavailable(val message: String) : BookSourceAddResult

    /** JSON 解析、下载等硬错误。 */
    data class Failed(val message: String) : BookSourceAddResult
}

class OnlineBookRepository(
    private val sourceDao: BookSourceDao,
    private val bookDao: OnlineBookDao,
    private val chapterDao: OnlineChapterDao,
    private val cachedContentDao: CachedContentDao,
    private val ruleEngine: BookSourceRuleEngine,
    private val client: OkHttpClient,
) {
    val sources: Flow<List<BookSourceEntity>> = sourceDao.observeAll()

    fun books(sourceId: Long): Flow<List<OnlineBookEntity>> =
        bookDao.observeBySource(sourceId)

    /**
     * 添加书源：先解析，再实际跑一次搜索/目录/正文检测，通过后才入库。
     * skipValidation 用于用户确认“仍然添加”时跳过网络检测。
     */
    suspend fun addSource(
        input: String,
        skipValidation: Boolean = false,
    ): BookSourceAddResult = withContext(Dispatchers.IO) {
        val json = try {
            val trimmed = input.trim()
            if (trimmed.startsWith("http://") || trimmed.startsWith("https://")) {
                fetchJson(trimmed)
            } else {
                input
            }
        } catch (error: Exception) {
            return@withContext BookSourceAddResult.Failed(
                "书源下载失败：${error.message ?: "无法访问该地址"}",
            )
        }

        val source = try {
            BookSourceImporter.fromJson(json)
        } catch (error: Exception) {
            return@withContext BookSourceAddResult.Failed(
                error.message ?: "书源 JSON 格式不正确",
            )
        }

        if (sourceDao.getByUrl(source.url) != null) {
            return@withContext BookSourceAddResult.Failed("书源「${source.name}」已经添加过了")
        }

        var message = "已添加（未检测）"
        var warning: String? = null
        if (!skipValidation) {
            val test = runCatching { ruleEngine.testSource(source) }
                .getOrElse {
                    BookSourceTestResult(
                        ok = false,
                        message = "书源不可用：${it.message ?: "检测失败"}",
                    )
                }
            if (!test.ok) {
                return@withContext BookSourceAddResult.Unavailable(test.message)
            }
            message = test.message
            warning = test.warning
        }

        val id = sourceDao.insert(source)
        BookSourceAddResult.Added(id = id, message = message, warning = warning)
    }

    private fun fetchJson(url: String): String {
        val request = Request.Builder()
            .url(url)
            .header(
                "User-Agent",
                "Mozilla/5.0 (Linux; Android 13) AppleWebKit/537.36 " +
                    "(KHTML, like Gecko) Chrome/124.0.0.0 Mobile Safari/537.36",
            )
            .build()
        val call = client.newCall(request)
        call.timeout().timeout(20, java.util.concurrent.TimeUnit.SECONDS)
        call.execute().use { response ->
            if (!response.isSuccessful) {
                throw IllegalStateException("HTTP ${response.code}")
            }
            return response.body?.string().orEmpty()
        }
    }

    suspend fun deleteSource(source: BookSourceEntity) = withContext(Dispatchers.IO) {
        val books = bookDao.observeBySource(source.id).first()
        books.forEach { book ->
            val chapters = chapterDao.getByBook(book.id)
            if (chapters.isNotEmpty()) {
                cachedContentDao.deleteByChapters(chapters.map { it.id })
                chapterDao.deleteByBook(book.id)
            }
        }
        bookDao.deleteBySource(source.id)
        sourceDao.delete(source)
    }

    suspend fun search(
        sourceId: Long,
        keyword: String,
        page: Int = 1,
    ): OperationResult<List<OnlineBookEntity>> = withContext(Dispatchers.IO) {
        val source = sourceDao.getById(sourceId)
            ?: return@withContext OperationResult.Error("书源不存在")
        runCatching {
            val books = ruleEngine.search(source, keyword, page)
            if (books.isEmpty()) {
                emptyList()
            } else {
                val ids = bookDao.insertAll(books)
                books.mapIndexed { index, book ->
                    val id = ids.getOrNull(index)?.takeIf { it > 0 } ?: book.id
                    book.copy(id = id)
                }
            }
        }.fold(
            onSuccess = { OperationResult.Success(it) },
            onFailure = { OperationResult.Error(it.message ?: "搜索失败") },
        )
    }

    suspend fun getBook(bookId: Long): OnlineBookEntity? =
        bookDao.getById(bookId)

    suspend fun detail(book: OnlineBookEntity): OperationResult<OnlineBookEntity> =
        withContext(Dispatchers.IO) {
            val source = sourceDao.getById(book.sourceId)
                ?: return@withContext OperationResult.Error("书源不存在")
            runCatching {
                val updated = ruleEngine.detail(source, book)
                bookDao.insertAll(listOf(updated))
                updated
            }.fold(
                onSuccess = { OperationResult.Success(it) },
                onFailure = { OperationResult.Error(it.message ?: "详情获取失败") },
            )
        }

    suspend fun chapters(book: OnlineBookEntity): OperationResult<List<OnlineChapterEntity>> =
        withContext(Dispatchers.IO) {
            val source = sourceDao.getById(book.sourceId)
                ?: return@withContext OperationResult.Error("书源不存在")
            runCatching {
                val chapters = ruleEngine.chapters(source, book)
                if (chapters.isNotEmpty()) {
                    chapterDao.deleteByBook(book.id)
                    chapterDao.insertAll(chapters)
                }
                // 必须重新查询：入库后章节才有自增 id，直接返回临时对象的 id 全是 0，
                // 会导致目录 LazyColumn key 重复而崩溃、点章节也永远打开第 1 章。
                val persisted = chapterDao.getByBook(book.id)
                if (persisted.isNotEmpty()) persisted else chapters
            }.fold(
                onSuccess = { OperationResult.Success(it) },
                onFailure = { OperationResult.Error(it.message ?: "目录获取失败") },
            )
        }

    suspend fun getChapters(bookId: Long): List<OnlineChapterEntity> =
        chapterDao.getByBook(bookId)

    suspend fun content(chapter: OnlineChapterEntity): OperationResult<String> =
        withContext(Dispatchers.IO) {
            cachedContentDao.get(chapter.id)?.let {
                return@withContext OperationResult.Success(it)
            }
            val source = sourceDao.getById(chapter.sourceId)
                ?: return@withContext OperationResult.Error("书源不存在")
            runCatching {
                val content = ruleEngine.content(source, chapter.url)
                if (content.isNotBlank()) {
                    cachedContentDao.insert(
                        CachedChapterContentEntity(chapterId = chapter.id, content = content),
                    )
                }
                content
            }.fold(
                onSuccess = { OperationResult.Success(it) },
                onFailure = { OperationResult.Error(it.message ?: "正文获取失败") },
            )
        }
}
