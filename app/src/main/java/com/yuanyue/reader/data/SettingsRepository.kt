package com.yuanyue.reader.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.settingsDataStore by preferencesDataStore(name = "reader_settings")

enum class ReaderTheme(val key: String) {
    WHITE("white"),
    SEPIA("sepia"),
    GREEN("green"),
    DARK("dark"),
}

enum class PageMode(val key: String) {
    HORIZONTAL("horizontal"),
    VERTICAL("vertical"),
}

data class ReaderSettings(
    val fontSizeSp: Float = 18f,
    val lineHeight: Float = 1.5f,
    val theme: ReaderTheme = ReaderTheme.WHITE,
    val pageMode: PageMode = PageMode.HORIZONTAL,
    val serifFont: Boolean = false,
)

class SettingsRepository(private val context: Context) {
    private object Keys {
        val fontSize = floatPreferencesKey("font_size_sp")
        val lineHeight = floatPreferencesKey("line_height")
        val theme = stringPreferencesKey("reader_theme")
        val pageMode = stringPreferencesKey("page_mode")
        val serifFont = booleanPreferencesKey("serif_font")
    }

    val settings: Flow<ReaderSettings> = context.settingsDataStore.data.map { prefs ->
        ReaderSettings(
            fontSizeSp = prefs[Keys.fontSize] ?: 18f,
            lineHeight = prefs[Keys.lineHeight] ?: 1.5f,
            theme = runCatching { ReaderTheme.valueOf(prefs[Keys.theme] ?: ReaderTheme.WHITE.name) }
                .getOrDefault(ReaderTheme.WHITE),
            pageMode = runCatching { PageMode.valueOf(prefs[Keys.pageMode] ?: PageMode.HORIZONTAL.name) }
                .getOrDefault(PageMode.HORIZONTAL),
            serifFont = prefs[Keys.serifFont] ?: false,
        )
    }

    suspend fun updateFontSize(value: Float) {
        context.settingsDataStore.edit { it[Keys.fontSize] = value }
    }

    suspend fun updateLineHeight(value: Float) {
        context.settingsDataStore.edit { it[Keys.lineHeight] = value }
    }

    suspend fun updateTheme(value: ReaderTheme) {
        context.settingsDataStore.edit { it[Keys.theme] = value.name }
    }

    suspend fun updatePageMode(value: PageMode) {
        context.settingsDataStore.edit { it[Keys.pageMode] = value.name }
    }

    suspend fun updateSerifFont(value: Boolean) {
        context.settingsDataStore.edit { it[Keys.serifFont] = value }
    }
}
