package com.yuanyue.reader.ui.online

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuanyue.reader.data.OnlineBookRepository
import com.yuanyue.reader.data.OperationResult
import com.yuanyue.reader.model.OnlineBookEntity
import com.yuanyue.reader.model.OnlineChapterEntity
import kotlinx.coroutines.launch

class OnlineBookDetailViewModel(
    private val bookId: Long,
    private val repository: OnlineBookRepository,
) : ViewModel() {
    var book by mutableStateOf<OnlineBookEntity?>(null)
        private set
    var chapters by mutableStateOf<List<OnlineChapterEntity>>(emptyList())
        private set
    var isLoading by mutableStateOf(true)
        private set
    var error by mutableStateOf<String?>(null)
        private set

    init {
        load()
    }

    fun retry() = load()

    private fun load() {
        isLoading = true
        error = null
        viewModelScope.launch {
            val loaded = repository.getBook(bookId)
            if (loaded == null) {
                error = "书籍不存在"
                isLoading = false
                return@launch
            }
            book = loaded

            when (val detailResult = repository.detail(loaded)) {
                is OperationResult.Success -> {
                    book = detailResult.data
                }

                is OperationResult.Error -> {
                    error = detailResult.message
                }
            }

            when (val chapterResult = repository.chapters(loaded)) {
                is OperationResult.Success -> {
                    chapters = chapterResult.data
                }

                is OperationResult.Error -> {
                    if (error == null) error = chapterResult.message
                }
            }
            isLoading = false
        }
    }
}
