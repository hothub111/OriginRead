package com.yuanyue.reader.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yuanyue.reader.core.AppContainer
import com.yuanyue.reader.core.viewModelFactory
import com.yuanyue.reader.ui.home.ArticleDetailScreen
import com.yuanyue.reader.ui.home.ArticleDetailViewModel
import com.yuanyue.reader.ui.home.ArticleListScreen
import com.yuanyue.reader.ui.home.ArticleListViewModel
import com.yuanyue.reader.ui.home.FeedListScreen
import com.yuanyue.reader.ui.home.FeedListViewModel
import com.yuanyue.reader.ui.home.HomeScreen
import com.yuanyue.reader.ui.home.HomeViewModel
import com.yuanyue.reader.ui.home.HotArticleScreen
import com.yuanyue.reader.ui.home.HotArticleViewModel
import com.yuanyue.reader.ui.home.HotSearchScreen
import com.yuanyue.reader.ui.home.HotSearchViewModel
import com.yuanyue.reader.ui.library.BookshelfScreen
import com.yuanyue.reader.ui.library.LibraryViewModel
import com.yuanyue.reader.ui.reader.ReaderScreen
import com.yuanyue.reader.ui.reader.ReaderViewModel
import com.yuanyue.reader.ui.online.BookSourceManagerScreen
import com.yuanyue.reader.ui.online.BookSourceManagerViewModel
import com.yuanyue.reader.ui.online.OnlineBookDetailScreen
import com.yuanyue.reader.ui.online.OnlineBookDetailViewModel
import com.yuanyue.reader.ui.online.OnlineLibraryViewModel
import com.yuanyue.reader.ui.online.OnlineReaderScreen
import com.yuanyue.reader.ui.online.OnlineReaderViewModel
import com.yuanyue.reader.ui.comic.ComicDetailScreen
import com.yuanyue.reader.ui.comic.ComicDetailViewModel
import com.yuanyue.reader.ui.comic.ComicLibraryViewModel
import com.yuanyue.reader.ui.comic.ComicReaderScreen
import com.yuanyue.reader.ui.comic.ComicReaderViewModel
import com.yuanyue.reader.ui.comic.ComicSourceManagerScreen
import com.yuanyue.reader.ui.comic.ComicSourceManagerViewModel
import com.yuanyue.reader.ui.settings.SettingsScreen
import com.yuanyue.reader.ui.settings.SettingsViewModel

@Composable
fun AppNavHost(container: AppContainer) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val topLevelRoutes = TopLevelDestination.entries.map { it.route }
    val isTopLevel = currentDestination?.route in topLevelRoutes

    Scaffold(
        bottomBar = {
            if (isTopLevel) {
                NavigationBar {
                    TopLevelDestination.entries.forEach { destination ->
                        val selected = currentDestination?.hierarchy?.any {
                            it.route == destination.route
                        } == true
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(destination.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = destination.icon,
                                    contentDescription = destination.label,
                                )
                            },
                            label = { Text(destination.label) },
                        )
                    }
                }
            }
        },
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(padding),
        ) {
            composable(Routes.HOME) {
                val viewModel: HomeViewModel = viewModel(
                    factory = viewModelFactory {
                        HomeViewModel(rssRepository = container.rssRepository)
                    },
                )
                HomeScreen(
                    viewModel = viewModel,
                    onOpenFeedManager = { navController.navigate(Routes.FEED_MANAGER) },
                    onOpenFeed = { feedId ->
                        navController.navigate(Routes.articleList(feedId))
                    },
                    onOpenArticle = { articleId ->
                        navController.navigate(Routes.articleDetail(articleId))
                    },
                    onOpenHotBoard = { navController.navigate(Routes.HOT) },
                )
            }

            composable(Routes.BOOKSHELF) {
                val localViewModel: LibraryViewModel = viewModel(
                    factory = viewModelFactory {
                        LibraryViewModel(container.libraryRepository)
                    },
                )
                val onlineViewModel: OnlineLibraryViewModel = viewModel(
                    factory = viewModelFactory {
                        OnlineLibraryViewModel(container.onlineBookRepository)
                    },
                )
                val comicViewModel: ComicLibraryViewModel = viewModel(
                    factory = viewModelFactory {
                        ComicLibraryViewModel(container.comicRepository)
                    },
                )
                BookshelfScreen(
                    localViewModel = localViewModel,
                    onlineViewModel = onlineViewModel,
                    comicViewModel = comicViewModel,
                    onOpenLocalBook = { bookId ->
                        navController.navigate(Routes.reader(bookId))
                    },
                    onOpenOnlineBook = { bookId ->
                        navController.navigate(Routes.onlineBook(bookId))
                    },
                    onOpenComic = { comicId ->
                        navController.navigate(Routes.comicDetail(comicId))
                    },
                    onOpenBookSourceManager = {
                        navController.navigate(Routes.BOOK_SOURCE_MANAGER)
                    },
                    onOpenComicSourceManager = {
                        navController.navigate(Routes.COMIC_SOURCE_MANAGER)
                    },
                )
            }

            composable(Routes.HOT) {
                val viewModel: HotSearchViewModel = viewModel(
                    factory = viewModelFactory {
                        HotSearchViewModel(container.hotSearchRepository)
                    },
                )
                HotSearchScreen(
                    viewModel = viewModel,
                    onOpenHotArticle = { title, url, subtitle ->
                        navController.navigate(Routes.hotArticle(title, url, subtitle))
                    },
                )
            }

            composable(Routes.SETTINGS) {
                val viewModel: SettingsViewModel = viewModel(
                    factory = viewModelFactory {
                        SettingsViewModel(container.settingsRepository)
                    },
                )
                SettingsScreen(viewModel = viewModel)
            }

            composable(Routes.FEED_MANAGER) {
                val viewModel: FeedListViewModel = viewModel(
                    factory = viewModelFactory {
                        FeedListViewModel(container.rssRepository)
                    },
                )
                FeedListScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                    onOpenFeed = { feedId ->
                        navController.navigate(Routes.articleList(feedId))
                    },
                )
            }

            composable(
                route = Routes.ARTICLE_LIST,
                arguments = listOf(navArgument("feedId") { type = NavType.LongType }),
            ) { entry ->
                val feedId = entry.arguments?.getLong("feedId") ?: 0L
                val viewModel: ArticleListViewModel = viewModel(
                    factory = viewModelFactory {
                        ArticleListViewModel(container.rssRepository, feedId)
                    },
                )
                ArticleListScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                    onOpenArticle = { articleId ->
                        navController.navigate(Routes.articleDetail(articleId))
                    },
                )
            }

            composable(Routes.BOOK_SOURCE_MANAGER) {
                val viewModel: BookSourceManagerViewModel = viewModel(
                    factory = viewModelFactory {
                        BookSourceManagerViewModel(container.onlineBookRepository)
                    },
                )
                BookSourceManagerScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                )
            }

            composable(Routes.COMIC_SOURCE_MANAGER) {
                val viewModel: ComicSourceManagerViewModel = viewModel(
                    factory = viewModelFactory {
                        ComicSourceManagerViewModel(container.comicRepository)
                    },
                )
                ComicSourceManagerScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                )
            }

            composable(
                route = Routes.ONLINE_BOOK,
                arguments = listOf(navArgument("bookId") { type = NavType.LongType }),
            ) { entry ->
                val bookId = entry.arguments?.getLong("bookId") ?: 0L
                val viewModel: OnlineBookDetailViewModel = viewModel(
                    factory = viewModelFactory {
                        OnlineBookDetailViewModel(
                            bookId = bookId,
                            repository = container.onlineBookRepository,
                        )
                    },
                )
                OnlineBookDetailScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                    onOpenChapter = { chapterId ->
                        navController.navigate(Routes.onlineReader(bookId, chapterId))
                    },
                )
            }

            composable(
                route = Routes.ONLINE_READER,
                arguments = listOf(
                    navArgument("bookId") { type = NavType.LongType },
                    navArgument("chapterId") { type = NavType.LongType },
                ),
            ) { entry ->
                val bookId = entry.arguments?.getLong("bookId") ?: 0L
                val chapterId = entry.arguments?.getLong("chapterId") ?: 0L
                val viewModel: OnlineReaderViewModel = viewModel(
                    factory = viewModelFactory {
                        OnlineReaderViewModel(
                            bookId = bookId,
                            chapterId = chapterId,
                            repository = container.onlineBookRepository,
                        )
                    },
                )
                OnlineReaderScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                )
            }

            composable(
                route = Routes.COMIC_DETAIL,
                arguments = listOf(navArgument("comicId") { type = NavType.LongType }),
            ) { entry ->
                val comicId = entry.arguments?.getLong("comicId") ?: 0L
                val viewModel: ComicDetailViewModel = viewModel(
                    factory = viewModelFactory {
                        ComicDetailViewModel(
                            comicId = comicId,
                            repository = container.comicRepository,
                        )
                    },
                )
                ComicDetailScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                    onOpenChapter = { chapterId ->
                        navController.navigate(Routes.comicReader(comicId, chapterId))
                    },
                )
            }

            composable(
                route = Routes.COMIC_READER,
                arguments = listOf(
                    navArgument("comicId") { type = NavType.LongType },
                    navArgument("chapterId") { type = NavType.LongType },
                ),
            ) { entry ->
                val comicId = entry.arguments?.getLong("comicId") ?: 0L
                val chapterId = entry.arguments?.getLong("chapterId") ?: 0L
                val viewModel: ComicReaderViewModel = viewModel(
                    factory = viewModelFactory {
                        ComicReaderViewModel(
                            comicId = comicId,
                            chapterId = chapterId,
                            repository = container.comicRepository,
                        )
                    },
                )
                ComicReaderScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                )
            }

            composable(
                route = Routes.ARTICLE_DETAIL,
                arguments = listOf(navArgument("articleId") { type = NavType.LongType }),
            ) { entry ->
                val articleId = entry.arguments?.getLong("articleId") ?: 0L
                val viewModel: ArticleDetailViewModel = viewModel(
                    factory = viewModelFactory {
                        ArticleDetailViewModel(container.rssRepository, articleId)
                    },
                )
                ArticleDetailScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                )
            }

            composable(
                route = Routes.READER,
                arguments = listOf(navArgument("bookId") { type = NavType.LongType }),
            ) { entry ->
                val bookId = entry.arguments?.getLong("bookId") ?: 0L
                val viewModel: ReaderViewModel = viewModel(
                    factory = viewModelFactory {
                        ReaderViewModel(
                            bookId = bookId,
                            libraryRepository = container.libraryRepository,
                            settingsRepository = container.settingsRepository,
                        )
                    },
                )
                ReaderScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                )
            }

            composable(
                route = Routes.HOT_ARTICLE,
                arguments = listOf(
                    navArgument("title") { type = NavType.StringType; defaultValue = "" },
                    navArgument("url") { type = NavType.StringType; defaultValue = "" },
                    navArgument("subtitle") { type = NavType.StringType; defaultValue = "" },
                ),
            ) { entry ->
                val title = entry.arguments?.getString("title").orEmpty()
                val url = entry.arguments?.getString("url").orEmpty()
                val subtitle = entry.arguments?.getString("subtitle").orEmpty()
                val viewModel: HotArticleViewModel = viewModel(
                    factory = viewModelFactory {
                        HotArticleViewModel(
                            title = title,
                            url = url,
                            subtitle = subtitle,
                            repository = container.articleContentRepository,
                        )
                    },
                )
                HotArticleScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                )
            }
        }
    }
}
