package com.yuanyue.reader.ui.online

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuanyue.reader.data.OnlineBookRepository
import com.yuanyue.reader.data.OperationResult
import com.yuanyue.reader.model.OnlineBookEntity
import com.yuanyue.reader.model.OnlineChapterEntity
import kotlinx.coroutines.launch

class OnlineReaderViewModel(
    private val bookId: Long,
    private val chapterId: Long,
    private val repository: OnlineBookRepository,
) : ViewModel() {
    var book by mutableStateOf<OnlineBookEntity?>(null)
        private set
    var chapters by mutableStateOf<List<OnlineChapterEntity>>(emptyList())
        private set
    var currentIndex by mutableIntStateOf(0)
        private set
    var content by mutableStateOf("")
        private set
    var isLoading by mutableStateOf(true)
        private set
    var error by mutableStateOf<String?>(null)
        private set

    init {
        load()
    }

    fun openChapter(chapter: OnlineChapterEntity) {
        val index = chapters.indexOfFirst { it.id == chapter.id }
        if (index >= 0) currentIndex = index
        loadContent(chapter)
    }

    fun retry() = load()

    private fun load() {
        isLoading = true
        error = null
        viewModelScope.launch {
            val loadedBook = repository.getBook(bookId)
            if (loadedBook == null) {
                error = "书籍不存在"
                isLoading = false
                return@launch
            }
            book = loadedBook
            chapters = repository.getChapters(bookId)
            currentIndex = chapters.indexOfFirst { it.id == chapterId }.coerceAtLeast(0)
            val chapter = chapters.getOrNull(currentIndex)
            if (chapter != null) {
                loadContent(chapter)
            } else {
                error = "章节不存在"
                isLoading = false
            }
        }
    }

    private fun loadContent(chapter: OnlineChapterEntity) {
        isLoading = true
        error = null
        viewModelScope.launch {
            when (val result = repository.content(chapter)) {
                is OperationResult.Success -> {
                    content = result.data
                    isLoading = false
                }

                is OperationResult.Error -> {
                    error = result.message
                    isLoading = false
                }
            }
        }
    }
}
