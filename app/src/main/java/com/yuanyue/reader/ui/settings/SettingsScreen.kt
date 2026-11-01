package com.yuanyue.reader.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.yuanyue.reader.data.PageMode
import com.yuanyue.reader.data.ReaderTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val settings by viewModel.settings.collectAsState(
        initial = com.yuanyue.reader.data.ReaderSettings(),
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("设置") })
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            SettingsGroup(title = "阅读排版") {
                Column {
                    SettingLabel("字号：${"%.0f".format(settings.fontSizeSp)}")
                    Slider(
                        value = settings.fontSizeSp,
                        onValueChange = viewModel::updateFontSize,
                        valueRange = 12f..30f,
                        steps = 17,
                    )
                }
                Column {
                    SettingLabel("行距：${"%.1f".format(settings.lineHeight)}")
                    Slider(
                        value = settings.lineHeight,
                        onValueChange = viewModel::updateLineHeight,
                        valueRange = 1.2f..2.2f,
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("衬线字体")
                        Text(
                            text = "更接近纸质书的宋体/衬线风格",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                    Switch(
                        checked = settings.serifFont,
                        onCheckedChange = viewModel::updateSerifFont,
                    )
                }
            }

            SettingsGroup(title = "阅读主题") {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ReaderTheme.entries.forEach { theme ->
                        FilterChip(
                            selected = settings.theme == theme,
                            onClick = { viewModel.updateTheme(theme) },
                            label = { Text(themeLabel(theme)) },
                        )
                    }
                }
            }

            SettingsGroup(title = "翻页方式") {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    PageMode.entries.forEach { mode ->
                        FilterChip(
                            selected = settings.pageMode == mode,
                            onClick = { viewModel.updatePageMode(mode) },
                            label = { Text(if (mode == PageMode.HORIZONTAL) "横向翻页" else "纵向滚动") },
                        )
                    }
                }
            }

            SettingsGroup(title = "关于") {
                Column {
                    Text(
                        text = "原阅",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = "版本 1.1.0",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text = "聚合资讯与本地阅读的极简阅读器",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsGroup(
    title: String,
    content: @Composable () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
        )
        content()
    }
}

@Composable
private fun SettingLabel(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}

private fun themeLabel(theme: ReaderTheme): String = when (theme) {
    ReaderTheme.WHITE -> "白纸"
    ReaderTheme.SEPIA -> "羊皮纸"
    ReaderTheme.GREEN -> "护眼绿"
    ReaderTheme.DARK -> "夜间"
}
