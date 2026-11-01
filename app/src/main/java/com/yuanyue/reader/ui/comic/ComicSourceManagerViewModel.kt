package com.yuanyue.reader.ui.comic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuanyue.reader.data.ComicRepository
import com.yuanyue.reader.model.ComicSourceEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ComicSourceManagerViewModel(
    private val repository: ComicRepository,
) : ViewModel() {
    val sources = repository.sources.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    suspend fun addSource(input: String) = repository.addSource(input)

    suspend fun deleteSource(source: ComicSourceEntity) = repository.deleteSource(source)
}
