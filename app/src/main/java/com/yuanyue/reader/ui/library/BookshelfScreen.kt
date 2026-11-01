package com.yuanyue.reader.ui.library

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.yuanyue.reader.data.OperationResult
import com.yuanyue.reader.ui.comic.ComicLibraryContent
import com.yuanyue.reader.ui.comic.ComicLibraryViewModel
import com.yuanyue.reader.ui.online.OnlineLibraryContent
import com.yuanyue.reader.ui.online.OnlineLibraryViewModel
import kotlinx.coroutines.launch

private data class BookshelfTab(val title: String)

private val bookshelfTabs = listOf(
    BookshelfTab("本地"),
    BookshelfTab("在线"),
    BookshelfTab("漫画"),
)

/**
 * 书架：把原来的「书库 / 小说 / 漫画」三个底部 Tab 合并为一个，
 * 用顶部 Tab 区分本地、在线与漫画，底部导航只保留 4 个入口。
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfScreen(
    localViewModel: LibraryViewModel,
    onlineViewModel: OnlineLibraryViewModel,
    comicViewModel: ComicLibraryViewModel,
    onOpenLocalBook: (Long) -> Unit,
    onOpenOnlineBook: (Long) -> Unit,
    onOpenComic: (Long) -> Unit,
    onOpenBookSourceManager: () -> Unit,
    onOpenComicSourceManager: () -> Unit,
) {
    var selectedTab by rememberSaveable { mutableStateOf(0) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val importLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
    ) { uri ->
        if (uri != null) {
            runCatching {
                context.contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION,
                )
            }
            scope.launch {
                val result = localViewModel.importBook(uri)
                val message = when (result) {
                    is OperationResult.Success -> "导入成功"
                    is OperationResult.Error -> result.message
                }
                snackbarHostState.showSnackbar(message)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("书架") },
                actions = {
                    when (selectedTab) {
                        0 -> {
                            if (localViewModel.isImporting) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .padding(end = 16.dp)
                                        .size(22.dp),
                                    strokeWidth = 2.dp,
                                )
                            } else {
                                IconButton(onClick = { importLauncher.launch(arrayOf("*/*")) }) {
                                    Icon(Icons.Default.Add, contentDescription = "导入本地书籍")
                                }
                            }
                        }

                        1 -> IconButton(onClick = onOpenBookSourceManager) {
                            Icon(Icons.Default.Cloud, contentDescription = "书源管理")
                        }

                        else -> IconButton(onClick = onOpenComicSourceManager) {
                            Icon(Icons.Default.Image, contentDescription = "漫画源管理")
                        }
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            TabRow(selectedTabIndex = selectedTab) {
                bookshelfTabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(tab.title) },
                    )
                }
            }

            when (selectedTab) {
                0 -> LocalBooksContent(
                    viewModel = localViewModel,
                    onOpenBook = onOpenLocalBook,
                    modifier = Modifier.fillMaxSize(),
                )

                1 -> OnlineLibraryContent(
                    viewModel = onlineViewModel,
                    onOpenBook = onOpenOnlineBook,
                    onOpenSourceManager = onOpenBookSourceManager,
                    modifier = Modifier.fillMaxSize(),
                )

                else -> ComicLibraryContent(
                    viewModel = comicViewModel,
                    onOpenComic = onOpenComic,
                    onOpenSourceManager = onOpenComicSourceManager,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
