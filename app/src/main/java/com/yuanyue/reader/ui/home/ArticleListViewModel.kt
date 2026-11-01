package com.yuanyue.reader.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuanyue.reader.data.OperationResult
import com.yuanyue.reader.data.RssRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ArticleListViewModel(
    private val rssRepository: RssRepository,
    feedId: Long,
) : ViewModel() {
    val feed = rssRepository.feeds
        .map { list -> list.firstOrNull { it.id == feedId } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    val articles = rssRepository.articlesForFeed(feedId).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    suspend fun refresh(): OperationResult<Unit> {
        val currentFeed = feed.value ?: return OperationResult.Error("订阅源不存在")
        return rssRepository.refreshFeed(currentFeed)
    }
}
