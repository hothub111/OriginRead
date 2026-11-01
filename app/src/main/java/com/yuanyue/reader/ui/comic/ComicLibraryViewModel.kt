package com.yuanyue.reader.ui.comic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuanyue.reader.data.ComicRepository
import com.yuanyue.reader.data.OperationResult
import com.yuanyue.reader.model.ComicSourceEntity
import com.yuanyue.reader.model.OnlineComicEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ComicLibraryViewModel(
    private val repository: ComicRepository,
) : ViewModel() {
    val sources = repository.sources.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    var selectedSourceId by mutableStateOf<Long?>(null)
        private set
    var comics by mutableStateOf<List<OnlineComicEntity>>(emptyList())
        private set
    var searchResults by mutableStateOf<List<OnlineComicEntity>>(emptyList())
        private set
    var isSearching by mutableStateOf(false)
        private set
    var error by mutableStateOf<String?>(null)
        private set

    init {
        // 漫画源可能在页面创建之后才添加，这里持续跟随源列表，自动选中第一个可用源。
        viewModelScope.launch {
            repository.sources.collect { list ->
                val current = selectedSourceId
                if (current == null || list.none { it.id == current }) {
                    val first = list.firstOrNull()
                    if (first != null) {
                        selectSource(first.id)
                    } else {
                        selectedSourceId = null
                        comics = emptyList()
                    }
                }
            }
        }
    }

    fun selectSource(sourceId: Long) {
        selectedSourceId = sourceId
        searchResults = emptyList()
        error = null
        viewModelScope.launch {
            repository.comics(sourceId).collect { list -> comics = list }
        }
    }

    fun search(keyword: String) {
        val sourceId = selectedSourceId ?: run {
            error = "请先添加并选择漫画源"
            return
        }
        if (keyword.isBlank()) {
            error = "请输入漫画名"
            return
        }
        isSearching = true
        error = null
        viewModelScope.launch {
            when (val result = repository.search(sourceId, keyword)) {
                is OperationResult.Success -> {
                    searchResults = result.data
                    isSearching = false
                }

                is OperationResult.Error -> {
                    error = result.message
                    isSearching = false
                }
            }
        }
    }
}
