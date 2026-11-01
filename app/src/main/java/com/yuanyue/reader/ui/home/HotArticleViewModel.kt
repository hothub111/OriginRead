package com.yuanyue.reader.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuanyue.reader.data.ArticleContentRepository
import com.yuanyue.reader.data.ExtractedArticle
import com.yuanyue.reader.data.OperationResult
import kotlinx.coroutines.launch

class HotArticleViewModel(
    private val title: String,
    private val url: String,
    private val subtitle: String,
    private val repository: ArticleContentRepository,
) : ViewModel() {
    var isLoading by mutableStateOf(true)
        private set
    var article by mutableStateOf<ExtractedArticle?>(null)
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
            when (val result = repository.extract(url, title)) {
                is OperationResult.Success -> {
                    article = result.data
                    isLoading = false
                }

                is OperationResult.Error -> {
                    article = ExtractedArticle(
                        title = title,
                        content = buildString {
                            if (subtitle.isNotBlank()) {
                                append("热搜热度：")
                                append(subtitle)
                                append("\n\n")
                            }
                            append("该平台暂未提供可读取的正文。")
                        },
                    )
                    isLoading = false
                }
            }
        }
    }
}
