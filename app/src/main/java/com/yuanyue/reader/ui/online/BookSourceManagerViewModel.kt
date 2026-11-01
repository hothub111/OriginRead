package com.yuanyue.reader.ui.online

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuanyue.reader.data.BookSourceAddResult
import com.yuanyue.reader.data.OnlineBookRepository
import com.yuanyue.reader.model.BookSourceEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class BookSourceManagerViewModel(
    private val repository: OnlineBookRepository,
) : ViewModel() {
    val sources = repository.sources.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    suspend fun addSource(
        json: String,
        skipValidation: Boolean = false,
    ): BookSourceAddResult = repository.addSource(json, skipValidation)

    suspend fun deleteSource(source: BookSourceEntity) = repository.deleteSource(source)
}
