package com.yuanyue.reader.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yuanyue.reader.data.parser.HtmlTextExtractor
import com.yuanyue.reader.ui.components.NativeArticleContent
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(
    viewModel: ArticleDetailViewModel,
    onBack: () -> Unit,
) {
    val article = viewModel.article
    val feedTitle = viewModel.feedTitle
    val text = remember(article) {
        article?.let {
            HtmlTextExtractor.toText(
                it.contentHtml
                    ?.takeIf { value -> value.isNotBlank() }
                    ?: it.description.orEmpty(),
            )
        }.orEmpty()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(feedTitle.ifBlank { "文章详情" }) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "返回")
                    }
                },
                actions = {
                    article?.let {
                        IconButton(onClick = viewModel::toggleFavorite) {
                            Icon(
                                imageVector = if (it.isFavorite) {
                                    Icons.Default.Favorite
                                } else {
                                    Icons.Default.FavoriteBorder
                                },
                                contentDescription = "收藏",
                            )
                        }
                    }
                },
            )
        },
    ) { padding ->
        if (article == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                CircularProgressIndicator()
            }
        } else {
            val subtitle = buildString {
                append(article.author?.takeIf { it.isNotBlank() } ?: feedTitle)
                article.publishedAt?.let {
                    append(" · ")
                    append(formatTime(it))
                }
            }
            NativeArticleContent(
                title = article.title,
                subtitle = subtitle,
                content = text,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            )
        }
    }
}

private fun formatTime(epochMillis: Long): String {
    return runCatching {
        val dateTime = Instant.ofEpochMilli(epochMillis)
            .atZone(ZoneId.systemDefault())
        DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm", Locale.CHINESE).format(dateTime)
    }.getOrDefault("")
}
