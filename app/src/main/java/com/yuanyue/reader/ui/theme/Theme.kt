package com.yuanyue.reader.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    background = PaperWhite,
    onBackground = ReaderInk,
    surface = WarmCard,
    onSurface = ReaderInk,
    surfaceVariant = ColorTokens.SurfaceVariant,
    onSurfaceVariant = SoftInk,
    outline = DividerGray,
)

private object ColorTokens {
    val SurfaceVariant = androidx.compose.ui.graphics.Color(0xFFF1F3F4)
}

@Composable
fun YuanyueTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = AppTypography,
        content = content,
    )
}
