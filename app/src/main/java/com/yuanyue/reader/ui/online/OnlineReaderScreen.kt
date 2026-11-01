package com.yuanyue.reader.ui.online

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.yuanyue.reader.ui.components.NativeArticleContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnlineReaderScreen(
    viewModel: OnlineReaderViewModel,
    onBack: () -> Unit,
) {
    val book = viewModel.book
    val chapters = viewModel.chapters
    val currentIndex = viewModel.currentIndex
    val currentChapter = chapters.getOrNull(currentIndex)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = currentChapter?.title ?: book?.title ?: "在线阅读",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "返回")
                    }
                },
            )
        },
        bottomBar = {
            Surface {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TextButton(
                        onClick = {
                            chapters.getOrNull(currentIndex - 1)?.let(viewModel::openChapter)
                        },
                        enabled = currentIndex > 0,
                    ) {
                        Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "上一章")
                    }
                    Text(
                        text = "${currentIndex + 1} / ${chapters.size}",
                        modifier = Modifier.weight(1f),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    )
                    TextButton(
                        onClick = {
                            chapters.getOrNull(currentIndex + 1)?.let(viewModel::openChapter)
                        },
                        enabled = currentIndex < chapters.size - 1,
                    ) {
                        Icon(Icons.Default.KeyboardArrowRight, contentDescription = "下一章")
                    }
                }
            }
        },
    ) { padding ->
        when {
            viewModel.isLoading -> {
                androidx.compose.foundation.layout.Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }

            viewModel.error != null -> {
                androidx.compose.foundation.layout.Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(24.dp),
                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(viewModel.error.orEmpty())
                    TextButton(onClick = viewModel::retry) {
                        Text("重新加载")
                    }
                }
            }

            else -> {
                NativeArticleContent(
                    title = currentChapter?.title ?: book?.title.orEmpty(),
                    subtitle = book?.author,
                    content = viewModel.content,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                )
            }
        }
    }
}
