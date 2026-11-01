package com.yuanyue.reader.ui.reader

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yuanyue.reader.data.PageMode
import com.yuanyue.reader.data.ReaderSettings
import com.yuanyue.reader.data.ReaderTheme
import com.yuanyue.reader.model.BookChapter
import com.yuanyue.reader.model.BookEntity
import kotlin.math.roundToInt

@Composable
fun ReaderScreen(
    viewModel: ReaderViewModel,
    onBack: () -> Unit,
) {
    val book = viewModel.book
    val chapters = viewModel.chapters
    val selectedChapter = viewModel.selectedChapter
    val loadError = viewModel.loadError
    val settings by viewModel.settings.collectAsState(initial = ReaderSettings())

    when {
        book == null && loadError == null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator()
                    Text(
                        text = "正在打开书籍…",
                        modifier = Modifier.padding(top = 14.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }

        book == null -> {
            Scaffold { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(loadError.orEmpty())
                    Row {
                        TextButton(onClick = viewModel::load) {
                            Text("重试")
                        }
                        TextButton(onClick = onBack) {
                            Text("返回书架")
                        }
                    }
                }
            }
        }

        chapters.isEmpty() -> {
            ReaderEmpty(onBack = onBack, onRetry = viewModel::load)
        }

        else -> {
            val chapter = chapters.getOrNull(selectedChapter) ?: chapters.first()
            ReaderContent(
                book = book,
                chapter = chapter,
                chapterIndex = selectedChapter,
                chapterCount = chapters.size,
                chapters = chapters,
                initialPage = viewModel.initialPage,
                settings = settings,
                onBack = onBack,
                onSelectChapter = viewModel::selectChapter,
                onProgress = viewModel::updateProgress,
                onFontSizeChange = viewModel::updateFontSize,
                onLineHeightChange = viewModel::updateLineHeight,
                onThemeChange = viewModel::updateTheme,
                onPageModeChange = viewModel::updatePageMode,
                onSerifFontChange = viewModel::updateSerifFont,
            )
        }
    }
}

@Composable
private fun ReaderEmpty(onBack: () -> Unit, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("暂时无法读取这本书的内容")
        Row {
            TextButton(onClick = onRetry) {
                Text("重试")
            }
            TextButton(onClick = onBack) {
                Text("返回")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun ReaderContent(
    book: BookEntity,
    chapter: BookChapter,
    chapterIndex: Int,
    chapterCount: Int,
    chapters: List<BookChapter>,
    initialPage: Int,
    settings: ReaderSettings,
    onBack: () -> Unit,
    onSelectChapter: (Int) -> Unit,
    onProgress: (Int, Int, Int) -> Unit,
    onFontSizeChange: (Float) -> Unit,
    onLineHeightChange: (Float) -> Unit,
    onThemeChange: (ReaderTheme) -> Unit,
    onPageModeChange: (PageMode) -> Unit,
    onSerifFontChange: (Boolean) -> Unit,
) {
    val palette = readerPalette(settings.theme)
    val density = LocalDensity.current
    var controlsVisible by remember { mutableStateOf(true) }
    var showToc by remember { mutableStateOf(false) }
    var showSettings by remember { mutableStateOf(false) }
    var currentPage by remember(chapterIndex) { mutableIntStateOf(initialPage.coerceAtLeast(0)) }

    val textStyle = TextStyle(
        color = palette.text,
        fontSize = settings.fontSizeSp.sp,
        lineHeight = settings.fontSizeSp.sp * settings.lineHeight,
        fontFamily = if (settings.serifFont) FontFamily.Serif else FontFamily.Default,
    )

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(palette.background)
            .pointerInput(Unit) {
                detectTapGestures { controlsVisible = !controlsVisible }
            },
    ) {
        val horizontalPadding = 24.dp
        val topPadding = 88.dp
        val bottomPadding = 72.dp
        val pageWidth = (maxWidth - horizontalPadding * 2)
            .let { with(density) { it.toPx() } }
            .roundToInt()
            .coerceAtLeast(1)
        val pageHeight = (maxHeight - topPadding - bottomPadding)
            .let { with(density) { it.toPx() } }
            .roundToInt()
            .coerceAtLeast(1)

        val pages = rememberPagedText(chapter.content, textStyle, pageWidth, pageHeight)

        if (pages == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(28.dp),
                        color = palette.text,
                        strokeWidth = 2.dp,
                    )
                    Text(
                        text = "正在排版本章…",
                        modifier = Modifier.padding(top = 12.dp),
                        color = palette.secondary,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        } else if (pages.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = horizontalPadding,
                        top = topPadding,
                        end = horizontalPadding,
                        bottom = bottomPadding,
                    ),
            ) {
                Text("本章暂无内容", color = palette.secondary)
            }
        } else if (settings.pageMode == PageMode.HORIZONTAL) {
            key(chapterIndex) {
                val startPage = initialPage.coerceIn(0, pages.lastIndex)
                val pagerState = rememberPagerState(
                    initialPage = startPage,
                    pageCount = { pages.size },
                )
                LaunchedEffect(pagerState, pages.size) {
                    snapshotFlow { pagerState.currentPage }.collect { page ->
                        currentPage = page
                        onProgress(chapterIndex, page, pages.size)
                    }
                }
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize(),
                ) { page ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                start = horizontalPadding,
                                top = topPadding,
                                end = horizontalPadding,
                                bottom = bottomPadding,
                            ),
                    ) {
                        BasicText(
                            text = pages[page],
                            style = textStyle,
                        )
                    }
                }
            }
        } else {
            key(chapterIndex) {
                val listState = rememberLazyListState(
                    initialFirstVisibleItemIndex = initialPage.coerceIn(0, pages.lastIndex),
                )
                LaunchedEffect(listState, pages.size) {
                    snapshotFlow { listState.firstVisibleItemIndex }.collect { index ->
                        currentPage = index
                        onProgress(chapterIndex, index, pages.size)
                    }
                }
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        start = horizontalPadding,
                        top = topPadding,
                        end = horizontalPadding,
                        bottom = bottomPadding,
                    ),
                ) {
                    itemsIndexed(pages) { _, page ->
                        BasicText(
                            text = page,
                            style = textStyle,
                            modifier = Modifier.padding(bottom = 10.dp),
                        )
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = controlsVisible,
            modifier = Modifier.align(Alignment.TopCenter),
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            ReaderTopBar(
                title = book.title,
                backgroundColor = palette.background,
                contentColor = palette.text,
                onBack = onBack,
                onToc = { showToc = true },
                onSettings = { showSettings = true },
            )
        }

        AnimatedVisibility(
            visible = controlsVisible,
            modifier = Modifier.align(Alignment.BottomCenter),
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            ReaderBottomBar(
                chapterTitle = chapter.title,
                chapterIndex = chapterIndex,
                chapterCount = chapterCount,
                pageInfo = pages?.takeIf { it.isNotEmpty() }?.let { pageList ->
                    "${(currentPage + 1).coerceAtMost(pageList.size)} / ${pageList.size}"
                },
                backgroundColor = palette.background,
                contentColor = palette.text,
                onSettings = { showSettings = true },
            )
        }
    }

    if (showToc) {
        val tocState = rememberLazyListState()
        LaunchedEffect(chapterIndex) {
            if (chapterIndex in chapters.indices) {
                tocState.scrollToItem(chapterIndex)
            }
        }
        ModalBottomSheet(
            onDismissRequest = { showToc = false },
            sheetState = rememberModalBottomSheetState(),
        ) {
            Text(
                text = "目录（${chapters.size}）",
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
            )
            LazyColumn(
                state = tocState,
                modifier = Modifier.heightIn(max = 480.dp),
                contentPadding = PaddingValues(bottom = 24.dp),
            ) {
                itemsIndexed(chapters, key = { index, _ -> index }) { index, item ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelectChapter(index)
                                showToc = false
                            }
                            .padding(horizontal = 20.dp, vertical = 12.dp),
                    ) {
                        Text(
                            text = item.title,
                            fontWeight = if (index == chapterIndex) {
                                FontWeight.Bold
                            } else {
                                FontWeight.Normal
                            },
                            color = if (index == chapterIndex) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurface
                            },
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
        }
    }

    if (showSettings) {
        ReaderSettingsSheet(
            settings = settings,
            onDismiss = { showSettings = false },
            onFontSizeChange = onFontSizeChange,
            onLineHeightChange = onLineHeightChange,
            onThemeChange = onThemeChange,
            onPageModeChange = onPageModeChange,
            onSerifFontChange = onSerifFontChange,
        )
    }
}

@Composable
private fun ReaderTopBar(
    title: String,
    backgroundColor: Color,
    contentColor: Color,
    onBack: () -> Unit,
    onToc: () -> Unit,
    onSettings: () -> Unit,
) {
    Surface(color = backgroundColor.copy(alpha = 0.94f)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 6.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "返回", tint = contentColor)
            }
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                color = contentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            IconButton(onClick = onToc) {
                Icon(Icons.Default.FormatListBulleted, contentDescription = "目录", tint = contentColor)
            }
            IconButton(onClick = onSettings) {
                Icon(Icons.Default.Settings, contentDescription = "阅读设置", tint = contentColor)
            }
        }
    }
}

@Composable
private fun ReaderBottomBar(
    chapterTitle: String,
    chapterIndex: Int,
    chapterCount: Int,
    pageInfo: String?,
    backgroundColor: Color,
    contentColor: Color,
    onSettings: () -> Unit,
) {
    Surface(color = backgroundColor.copy(alpha = 0.94f)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = chapterTitle,
                modifier = Modifier.weight(1f),
                color = contentColor,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "${chapterIndex + 1} / $chapterCount",
                modifier = Modifier.padding(start = 12.dp),
                color = contentColor,
                style = MaterialTheme.typography.bodySmall,
            )
            pageInfo?.let {
                Text(
                    text = it,
                    modifier = Modifier.padding(start = 12.dp),
                    color = contentColor,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReaderSettingsSheet(
    settings: ReaderSettings,
    onDismiss: () -> Unit,
    onFontSizeChange: (Float) -> Unit,
    onLineHeightChange: (Float) -> Unit,
    onThemeChange: (ReaderTheme) -> Unit,
    onPageModeChange: (PageMode) -> Unit,
    onSerifFontChange: (Boolean) -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .padding(bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            Text(
                text = "阅读设置",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
            )
            Column {
                Text("字号：${"%.0f".format(settings.fontSizeSp)}")
                Slider(
                    value = settings.fontSizeSp,
                    onValueChange = onFontSizeChange,
                    valueRange = 12f..30f,
                )
            }
            Column {
                Text("行距：${"%.1f".format(settings.lineHeight)}")
                Slider(
                    value = settings.lineHeight,
                    onValueChange = onLineHeightChange,
                    valueRange = 1.2f..2.2f,
                )
            }
            Text("主题")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ReaderTheme.entries.forEach { theme ->
                    FilterChip(
                        selected = settings.theme == theme,
                        onClick = { onThemeChange(theme) },
                        label = { Text(readerThemeLabel(theme)) },
                    )
                }
            }
            Text("翻页")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                PageMode.entries.forEach { mode ->
                    FilterChip(
                        selected = settings.pageMode == mode,
                        onClick = { onPageModeChange(mode) },
                        label = { Text(if (mode == PageMode.HORIZONTAL) "横向翻页" else "纵向滚动") },
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("衬线字体")
                    Text(
                        text = "使用更接近纸质书的宋体/衬线风格",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
                Switch(
                    checked = settings.serifFont,
                    onCheckedChange = onSerifFontChange,
                )
            }
        }
    }
}

private data class ReaderPalette(
    val background: Color,
    val text: Color,
    val secondary: Color,
)

private fun readerPalette(theme: ReaderTheme): ReaderPalette = when (theme) {
    ReaderTheme.WHITE -> ReaderPalette(
        background = Color(0xFFFAFAF8),
        text = Color(0xFF202124),
        secondary = Color(0xFF7A7A7A),
    )

    ReaderTheme.SEPIA -> ReaderPalette(
        background = Color(0xFFF5EBD8),
        text = Color(0xFF4A3728),
        secondary = Color(0xFF8B7564),
    )

    ReaderTheme.GREEN -> ReaderPalette(
        background = Color(0xFFE7F1E7),
        text = Color(0xFF31473A),
        secondary = Color(0xFF65786B),
    )

    ReaderTheme.DARK -> ReaderPalette(
        background = Color(0xFF141414),
        text = Color(0xFFB8B8B8),
        secondary = Color(0xFF6D6D6D),
    )
}

private fun readerThemeLabel(theme: ReaderTheme): String = when (theme) {
    ReaderTheme.WHITE -> "白纸"
    ReaderTheme.SEPIA -> "羊皮纸"
    ReaderTheme.GREEN -> "护眼绿"
    ReaderTheme.DARK -> "夜间"
}
