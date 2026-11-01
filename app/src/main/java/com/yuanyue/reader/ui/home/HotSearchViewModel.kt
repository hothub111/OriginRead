package com.yuanyue.reader.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuanyue.reader.data.HotSearchItem
import com.yuanyue.reader.data.HotSearchPlatform
import com.yuanyue.reader.data.HotSearchRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HotSearchViewModel(
    private val repository: HotSearchRepository,
) : ViewModel() {
    var selectedPlatform by mutableStateOf(HotSearchPlatform.WEIBO)
        private set
    var items by mutableStateOf<List<HotSearchItem>>(emptyList())
        private set
    var isLoading by mutableStateOf(true)
        private set
    var error by mutableStateOf<String?>(null)
        private set
    var warning by mutableStateOf<String?>(null)
        private set
    var fromCache by mutableStateOf(false)
        private set

    private var loadJob: Job? = null

    init {
        load(HotSearchPlatform.WEIBO)
    }

    fun selectPlatform(platform: HotSearchPlatform) {
        if (selectedPlatform == platform && items.isNotEmpty()) return
        selectedPlatform = platform
        load(platform)
    }

    fun retry() = load(selectedPlatform)

    private fun load(platform: HotSearchPlatform) {
        loadJob?.cancel()
        isLoading = true
        error = null
        warning = null
        fromCache = false
        loadJob = viewModelScope.launch {
            try {
                val result = repository.fetch(platform)
                if (selectedPlatform != platform) return@launch
                items = result.items
                warning = result.warning
                fromCache = result.fromCache
            } catch (cancelled: CancellationException) {
                throw cancelled
            } catch (failure: Exception) {
                if (selectedPlatform != platform) return@launch
                items = emptyList()
                error = failure.message ?: "热榜加载失败，请稍后重试"
            } finally {
                if (selectedPlatform == platform) {
                    isLoading = false
                }
            }
        }
    }
}
