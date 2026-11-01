package com.yuanyue.reader.ui.navigation

import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.ui.graphics.vector.ImageVector

object Routes {
    const val HOME = "home"
    const val BOOKSHELF = "bookshelf"
    const val HOT = "hot"
    const val SETTINGS = "settings"
    const val BOOK_SOURCE_MANAGER = "book_source_manager"
    const val COMIC_SOURCE_MANAGER = "comic_source_manager"
    const val FEED_MANAGER = "feed_manager"
    const val ARTICLE_LIST = "article_list/{feedId}"
    const val ARTICLE_DETAIL = "article_detail/{articleId}"
    const val READER = "reader/{bookId}"
    const val HOT_ARTICLE = "hot_article?title={title}&url={url}&subtitle={subtitle}"
    const val ONLINE_BOOK = "online_book/{bookId}"
    const val ONLINE_READER = "online_reader/{bookId}/{chapterId}"
    const val COMIC_DETAIL = "comic_detail/{comicId}"
    const val COMIC_READER = "comic_reader/{comicId}/{chapterId}"

    fun articleList(feedId: Long) = "article_list/$feedId"
    fun articleDetail(articleId: Long) = "article_detail/$articleId"
    fun reader(bookId: Long) = "reader/$bookId"
    fun hotArticle(title: String, url: String, subtitle: String): String =
        "hot_article?title=${Uri.encode(title)}&url=${Uri.encode(url)}&subtitle=${Uri.encode(subtitle)}"
    fun onlineBook(bookId: Long) = "online_book/$bookId"
    fun onlineReader(bookId: Long, chapterId: Long) =
        "online_reader/$bookId/$chapterId"
    fun comicDetail(comicId: Long) = "comic_detail/$comicId"
    fun comicReader(comicId: Long, chapterId: Long) =
        "comic_reader/$comicId/$chapterId"
}

enum class TopLevelDestination(
    val route: String,
    val label: String,
    val icon: ImageVector,
) {
    HOME(Routes.HOME, "首页", Icons.Default.Home),
    BOOKSHELF(Routes.BOOKSHELF, "书架", Icons.Default.MenuBook),
    HOT(Routes.HOT, "热榜", Icons.Default.Whatshot),
    SETTINGS(Routes.SETTINGS, "设置", Icons.Default.Settings),
}
