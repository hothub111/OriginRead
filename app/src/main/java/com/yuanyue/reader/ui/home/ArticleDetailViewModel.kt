package com.yuanyue.reader.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuanyue.reader.data.RssRepository
import com.yuanyue.reader.model.RssArticleEntity
import kotlinx.coroutines.launch

class ArticleDetailViewModel(
    private val rssRepository: RssRepository,
    private val articleId: Long,
) : ViewModel() {
    var article by mutableStateOf<RssArticleEntity?>(null)
        private set
    var feedTitle by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            val loaded = rssRepository.getArticle(articleId)
            article = loaded
            if (loaded != null) {
                rssRepository.markRead(loaded.id, true)
                feedTitle = rssRepository.getFeed(loaded.feedId)?.title.orEmpty()
            }
        }
    }

    fun toggleFavorite() {
        val current = article ?: return
        viewModelScope.launch {
            rssRepository.toggleFavorite(current)
            article = current.copy(isFavorite = !current.isFavorite)
        }
    }
}
