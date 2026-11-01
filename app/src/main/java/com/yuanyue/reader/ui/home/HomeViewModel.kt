package com.yuanyue.reader.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuanyue.reader.data.RssRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val rssRepository: RssRepository,
) : ViewModel() {
    val feeds = rssRepository.feeds.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    val latestArticles = rssRepository.articles.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    var isRefreshing by mutableStateOf(false)
        private set

    init {
        refreshAll()
    }

    fun refreshAll() {
        if (isRefreshing) return
        isRefreshing = true
        viewModelScope.launch {
            runCatching { rssRepository.refreshAll() }
            isRefreshing = false
        }
    }
}
