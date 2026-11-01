package com.yuanyue.reader.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yuanyue.reader.model.BookEntity
import com.yuanyue.reader.model.BookFormat
import com.yuanyue.reader.model.BookSourceEntity
import com.yuanyue.reader.model.CachedChapterContentEntity
import com.yuanyue.reader.model.ComicChapterEntity
import com.yuanyue.reader.model.ComicSourceEntity
import com.yuanyue.reader.model.OnlineBookEntity
import com.yuanyue.reader.model.OnlineChapterEntity
import com.yuanyue.reader.model.OnlineComicEntity
import com.yuanyue.reader.model.RssArticleEntity
import com.yuanyue.reader.model.RssFeedEntity

@Database(
    entities = [
        RssFeedEntity::class,
        RssArticleEntity::class,
        BookEntity::class,
        BookSourceEntity::class,
        OnlineBookEntity::class,
        OnlineChapterEntity::class,
        CachedChapterContentEntity::class,
        ComicSourceEntity::class,
        OnlineComicEntity::class,
        ComicChapterEntity::class,
    ],
    version = 3,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rssFeedDao(): RssFeedDao
    abstract fun rssArticleDao(): RssArticleDao
    abstract fun bookDao(): BookDao
    abstract fun bookSourceDao(): BookSourceDao
    abstract fun onlineBookDao(): OnlineBookDao
    abstract fun onlineChapterDao(): OnlineChapterDao
    abstract fun cachedContentDao(): CachedContentDao
    abstract fun comicSourceDao(): ComicSourceDao
    abstract fun onlineComicDao(): OnlineComicDao
    abstract fun comicChapterDao(): ComicChapterDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "yuanyue_reader.db",
                )
                    .fallbackToDestructiveMigration()
                    .fallbackToDestructiveMigrationOnDowngrade()
                    .build()
                    .also { instance = it }
            }
        }
    }
}
