package com.yuanyue.reader.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuanyue.reader.model.RssArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RssArticleDao {
    @Query("SELECT * FROM rss_articles ORDER BY publishedAt DESC")
    fun observeAll(): Flow<List<RssArticleEntity>>

    @Query("SELECT * FROM rss_articles WHERE feedId = :feedId ORDER BY publishedAt DESC")
    fun observeByFeed(feedId: Long): Flow<List<RssArticleEntity>>

    @Query("SELECT * FROM rss_articles WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): RssArticleEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(articles: List<RssArticleEntity>)

    @Query("UPDATE rss_articles SET isRead = :read WHERE id = :articleId")
    suspend fun updateRead(articleId: Long, read: Boolean)

    @Query("UPDATE rss_articles SET isFavorite = :favorite WHERE id = :articleId")
    suspend fun updateFavorite(articleId: Long, favorite: Boolean)

    @Query("DELETE FROM rss_articles WHERE feedId = :feedId")
    suspend fun deleteByFeed(feedId: Long)
}
