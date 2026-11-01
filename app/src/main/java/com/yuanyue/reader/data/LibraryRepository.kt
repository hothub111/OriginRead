package com.yuanyue.reader.data

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import com.yuanyue.reader.core.db.BookDao
import com.yuanyue.reader.data.parser.EpubParser
import com.yuanyue.reader.data.parser.TextFileReader
import com.yuanyue.reader.data.parser.TxtChapterParser
import com.yuanyue.reader.model.BookChapter
import com.yuanyue.reader.model.BookEntity
import com.yuanyue.reader.model.BookFormat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class LibraryRepository(
    private val context: Context,
    private val bookDao: BookDao,
) {
    val books: Flow<List<BookEntity>> = bookDao.observeAll()

    fun observeBook(bookId: Long): Flow<BookEntity?> =
        bookDao.observeAll().map { list -> list.firstOrNull { it.id == bookId } }

    suspend fun getBook(bookId: Long): BookEntity? = bookDao.getById(bookId)

    suspend fun importBook(uri: Uri, suggestedName: String? = null): OperationResult<Long> =
        withContext(Dispatchers.IO) {
            runCatching {
                val name = queryDisplayName(uri) ?: suggestedName ?: "未命名书籍"
                val extension = name.substringAfterLast('.', "").lowercase()
                val format = when (extension) {
                    "epub" -> BookFormat.EPUB
                    else -> BookFormat.TXT
                }

                val bookDir = File(context.filesDir, "books").apply { mkdirs() }
                val safeName = sanitizeFileName(name)
                val destFile = File(bookDir, "${System.currentTimeMillis()}_$safeName")
                copyUriToFile(uri, destFile)

                val book = when (format) {
                    BookFormat.EPUB -> {
                        val coverDir = File(context.filesDir, "covers").apply { mkdirs() }
                        val parsed = EpubParser().parse(destFile, coverDir)
                        BookEntity(
                            title = parsed.title,
                            author = parsed.author,
                            localPath = destFile.absolutePath,
                            fileName = name,
                            format = format,
                            coverPath = parsed.coverPath,
                        )
                    }

                    BookFormat.TXT -> {
                        BookEntity(
                            title = name.substringBeforeLast('.', name),
                            author = null,
                            localPath = destFile.absolutePath,
                            fileName = name,
                            format = format,
                        )
                    }
                }
                bookDao.insert(book)
            }.fold(
                onSuccess = { OperationResult.Success(it) },
                onFailure = { OperationResult.Error(it.message ?: "导入失败") },
            )
        }

    suspend fun loadChapters(book: BookEntity): List<BookChapter> = withContext(Dispatchers.IO) {
        val file = File(book.localPath)
        if (!file.exists()) return@withContext emptyList()
        when (book.format) {
            BookFormat.TXT -> runCatching {
                val content = TextFileReader.readSmart(file)
                TxtChapterParser.split(content).ifEmpty {
                    if (content.isBlank()) {
                        emptyList()
                    } else {
                        listOf(BookChapter(title = book.title, content = content))
                    }
                }
            }.getOrDefault(emptyList())

            BookFormat.EPUB -> runCatching {
                EpubParser().parse(file, File(context.filesDir, "covers")).chapters
            }.getOrDefault(emptyList())
        }
    }

    suspend fun updateProgress(
        bookId: Long,
        chapterIndex: Int,
        chapterCount: Int,
        pageIndex: Int,
        pageCount: Int,
    ) {
        if (chapterCount <= 0) return
        val pageProgress = if (pageCount > 0) {
            (pageIndex + 1).toFloat() / pageCount.toFloat()
        } else {
            0f
        }
        val progress = ((chapterIndex + pageProgress.coerceIn(0f, 1f)) / chapterCount)
            .coerceIn(0f, 1f)
        bookDao.updateProgress(
            bookId = bookId,
            chapterIndex = chapterIndex,
            pageIndex = pageIndex,
            progress = progress,
        )
    }

    suspend fun deleteBook(book: BookEntity) {
        withContext(Dispatchers.IO) {
            runCatching { File(book.localPath).delete() }
            book.coverPath?.let { runCatching { File(it).delete() } }
            bookDao.delete(book)
        }
    }

    private fun queryDisplayName(uri: Uri): String? {
        return runCatching {
            context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                val index = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (index >= 0 && cursor.moveToFirst()) cursor.getString(index) else null
            }
        }.getOrNull()
    }

    private fun copyUriToFile(uri: Uri, dest: File) {
        context.contentResolver.openInputStream(uri)?.use { input ->
            FileOutputStream(dest).use { output ->
                input.copyTo(output)
            }
        } ?: throw IllegalArgumentException("无法读取所选文件")
    }

    private fun sanitizeFileName(name: String): String {
        return name.replace(Regex("[\\\\/:*?\"<>|]"), "_")
    }
}
