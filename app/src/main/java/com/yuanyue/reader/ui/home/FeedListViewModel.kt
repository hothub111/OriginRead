package com.yuanyue.reader.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuanyue.reader.data.OperationResult
import com.yuanyue.reader.data.RssRepository
import com.yuanyue.reader.model.RssFeedEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class FeedListViewModel(
    private val rssRepository: RssRepository,
) : ViewModel() {
    val feeds = rssRepository.feeds.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    suspend fun addFeed(url: String): OperationResult<Long> = rssRepository.addFeed(url)

    suspend fun refreshFeed(feed: RssFeedEntity): OperationResult<Unit> =
        rssRepository.refreshFeed(feed)

    suspend fun deleteFeed(feed: RssFeedEntity) = rssRepository.deleteFeed(feed)
}
