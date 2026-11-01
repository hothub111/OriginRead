package com.yuanyue.reader.ui.reader

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuanyue.reader.data.LibraryRepository
import com.yuanyue.reader.data.PageMode
import com.yuanyue.reader.data.ReaderSettings
import com.yuanyue.reader.data.ReaderTheme
import com.yuanyue.reader.data.SettingsRepository
import com.yuanyue.reader.model.BookChapter
import com.yuanyue.reader.model.BookEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ReaderViewModel(
    private val bookId: Long,
    private val libraryRepository: LibraryRepository,
    private val settingsRepository: SettingsRepository,
) : ViewModel() {
    var book by mutableStateOf<BookEntity?>(null)
        private set
    var chapters by mutableStateOf<List<BookChapter>>(emptyList())
        private set
    var selectedChapter by mutableIntStateOf(0)
        private set
    var initialPage by mutableIntStateOf(0)
        private set
    var loadError by mutableStateOf<String?>(null)
        private set
    var isLoading by mutableStateOf(true)
        private set

    val settings = settingsRepository.settings.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ReaderSettings(),
    )

    init {
        load()
    }

    fun load() {
        isLoading = true
        loadError = null
        viewModelScope.launch {
            try {
                val loadedBook = libraryRepository.getBook(bookId)
                if (loadedBook == null) {
                    loadError = "书籍不存在或已被删除"
                    return@launch
                }
                val loadedChapters = libraryRepository.loadChapters(loadedBook)
                if (loadedChapters.isEmpty()) {
                    book = loadedBook
                    chapters = emptyList()
                    loadError = "没有读取到章节内容，文件可能已损坏"
                    return@launch
                }
                book = loadedBook
                chapters = loadedChapters
                selectedChapter = loadedBook.lastReadPosition
                    .coerceIn(0, loadedChapters.size - 1)
                initialPage = loadedBook.totalPages.coerceAtLeast(0)
            } catch (error: Exception) {
                loadError = "打开失败：${error.message ?: "无法读取这本书"}"
            } finally {
                isLoading = false
            }
        }
    }

    fun selectChapter(index: Int) {
        if (index in chapters.indices) {
            selectedChapter = index
            initialPage = 0
        }
    }

    /**
     * lastReadPosition 保存章节下标，totalPages 保存该章节内的页下标。
     * 旧版本把“页”当成“章”保存，这里通过 coerce 保证不会越界。
     */
    fun updateProgress(chapterIndex: Int, pageIndex: Int, pageCount: Int) {
        val currentBook = book ?: return
        val chapterCount = chapters.size
        if (chapterCount == 0) return
        viewModelScope.launch {
            libraryRepository.updateProgress(
                bookId = currentBook.id,
                chapterIndex = chapterIndex.coerceIn(0, chapterCount - 1),
                chapterCount = chapterCount,
                pageIndex = pageIndex.coerceAtLeast(0),
                pageCount = pageCount.coerceAtLeast(0),
            )
        }
    }

    fun updateFontSize(value: Float) {
        viewModelScope.launch { settingsRepository.updateFontSize(value) }
    }

    fun updateLineHeight(value: Float) {
        viewModelScope.launch { settingsRepository.updateLineHeight(value) }
    }

    fun updateTheme(value: ReaderTheme) {
        viewModelScope.launch { settingsRepository.updateTheme(value) }
    }

    fun updatePageMode(value: PageMode) {
        viewModelScope.launch { settingsRepository.updatePageMode(value) }
    }

    fun updateSerifFont(value: Boolean) {
        viewModelScope.launch { settingsRepository.updateSerifFont(value) }
    }
}
