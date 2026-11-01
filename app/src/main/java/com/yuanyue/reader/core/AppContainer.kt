package com.yuanyue.reader.core

import android.content.Context
import com.yuanyue.reader.core.db.AppDatabase
import com.yuanyue.reader.data.ArticleContentRepository
import com.yuanyue.reader.data.BookSourceRuleEngine
import com.yuanyue.reader.data.ComicRepository
import com.yuanyue.reader.data.ComicSourceRuleEngine
import com.yuanyue.reader.data.HotSearchRepository
import com.yuanyue.reader.data.LibraryRepository
import com.yuanyue.reader.data.RssClient
import com.yuanyue.reader.data.RssRepository
import com.yuanyue.reader.data.OnlineBookRepository
import com.yuanyue.reader.data.SettingsRepository

class AppContainer(context: Context) {
    private val appContext = context.applicationContext
    private val database = AppDatabase.getInstance(appContext)

    val settingsRepository = SettingsRepository(appContext)
    val rssClient = RssClient()
    val rssRepository = RssRepository(
        feedDao = database.rssFeedDao(),
        articleDao = database.rssArticleDao(),
        rssClient = rssClient,
    )
    val hotSearchRepository = HotSearchRepository(rssClient.client, appContext)
    val articleContentRepository = ArticleContentRepository(rssClient.client)
    val bookSourceRuleEngine = BookSourceRuleEngine(rssClient.client)
    val comicSourceRuleEngine = ComicSourceRuleEngine(rssClient.client)
    val onlineBookRepository = OnlineBookRepository(
        sourceDao = database.bookSourceDao(),
        bookDao = database.onlineBookDao(),
        chapterDao = database.onlineChapterDao(),
        cachedContentDao = database.cachedContentDao(),
        ruleEngine = bookSourceRuleEngine,
        client = rssClient.client,
    )
    val libraryRepository = LibraryRepository(
        context = appContext,
        bookDao = database.bookDao(),
    )
    val comicRepository = ComicRepository(
        sourceDao = database.comicSourceDao(),
        comicDao = database.onlineComicDao(),
        chapterDao = database.comicChapterDao(),
        ruleEngine = comicSourceRuleEngine,
        client = rssClient.client,
    )
}
