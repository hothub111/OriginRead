package com.yuanyue.reader.core.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuanyue.reader.model.RssFeedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RssFeedDao {
    @Query("SELECT * FROM rss_feeds ORDER BY createdAt ASC")
    fun observeAll(): Flow<List<RssFeedEntity>>

    @Query("SELECT * FROM rss_feeds WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): RssFeedEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(feed: RssFeedEntity): Long

    @Delete
    suspend fun delete(feed: RssFeedEntity)
}
