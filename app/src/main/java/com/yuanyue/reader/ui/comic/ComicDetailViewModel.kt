package com.yuanyue.reader.ui.comic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuanyue.reader.data.ComicRepository
import com.yuanyue.reader.data.OperationResult
import com.yuanyue.reader.model.ComicChapterEntity
import com.yuanyue.reader.model.OnlineComicEntity
import kotlinx.coroutines.launch

class ComicDetailViewModel(
    private val comicId: Long,
    private val repository: ComicRepository,
) : ViewModel() {
    var comic by mutableStateOf<OnlineComicEntity?>(null)
        private set
    var chapters by mutableStateOf<List<ComicChapterEntity>>(emptyList())
        private set
    var isLoading by mutableStateOf(true)
        private set
    var error by mutableStateOf<String?>(null)
        private set

    init { load() }

    fun retry() = load()

    private fun load() {
        isLoading = true
        error = null
        viewModelScope.launch {
            val loaded = repository.getComic(comicId)
            if (loaded == null) {
                error = "漫画不存在"
                isLoading = false
                return@launch
            }
            comic = loaded
            when (val result = repository.detail(loaded)) {
                is OperationResult.Success -> comic = result.data
                is OperationResult.Error -> error = result.message
            }
            when (val result = repository.chapters(loaded)) {
                is OperationResult.Success -> chapters = result.data
                is OperationResult.Error -> if (error == null) error = result.message
            }
            isLoading = false
        }
    }
}
