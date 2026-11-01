package com.yuanyue.reader.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuanyue.reader.data.PageMode
import com.yuanyue.reader.data.ReaderTheme
import com.yuanyue.reader.data.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {
    val settings = settingsRepository.settings.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = com.yuanyue.reader.data.ReaderSettings(),
    )

    fun updateFontSize(value: Float) {
        viewModelScope.launch { settingsRepository.updateFontSize(value) }
    }

    fun updateLineHeight(value: Float) {
        viewModelScope.launch { settingsRepository.updateLineHeight(value) }
    }

    fun updateTheme(value: ReaderTheme) {
        viewModelScope.launch { settingsRepository.updateTheme(value) }
    }

    fun updatePageMode(value: PageMode) {
        viewModelScope.launch { settingsRepository.updatePageMode(value) }
    }

    fun updateSerifFont(value: Boolean) {
        viewModelScope.launch { settingsRepository.updateSerifFont(value) }
    }
}
