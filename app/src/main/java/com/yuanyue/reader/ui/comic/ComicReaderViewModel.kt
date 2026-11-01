package com.yuanyue.reader.ui.comic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuanyue.reader.data.ComicRepository
import com.yuanyue.reader.data.OperationResult
import com.yuanyue.reader.model.ComicChapterEntity
import com.yuanyue.reader.model.ComicImageItem
import kotlinx.coroutines.launch

class ComicReaderViewModel(
    private val comicId: Long,
    private val chapterId: Long,
    private val repository: ComicRepository,
) : ViewModel() {
    var chapters by mutableStateOf<List<ComicChapterEntity>>(emptyList())
        private set
    var currentIndex by mutableIntStateOf(0)
        private set
    var images by mutableStateOf<List<ComicImageItem>>(emptyList())
        private set
    var isLoading by mutableStateOf(true)
        private set
    var error by mutableStateOf<String?>(null)
        private set

    init { load() }

    fun openChapter(chapter: ComicChapterEntity) {
        val index = chapters.indexOfFirst { it.id == chapter.id }
        if (index >= 0) currentIndex = index
        loadImages(chapter)
    }

    fun retry() = load()

    private fun load() {
        isLoading = true
        error = null
        viewModelScope.launch {
            chapters = repository.getChapters(comicId)
            currentIndex = chapters.indexOfFirst { it.id == chapterId }.coerceAtLeast(0)
            val chapter = chapters.getOrNull(currentIndex)
            if (chapter != null) loadImages(chapter) else {
                error = "章节不存在"
                isLoading = false
            }
        }
    }

    private fun loadImages(chapter: ComicChapterEntity) {
        isLoading = true
        error = null
        viewModelScope.launch {
            when (val result = repository.images(comicId, chapter.id)) {
                is OperationResult.Success -> {
                    images = result.data
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
