package com.yuanyue.reader.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuanyue.reader.model.OnlineComicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OnlineComicDao {
    @Query("SELECT * FROM online_comics WHERE sourceId = :sourceId ORDER BY cachedAt DESC")
    fun observeBySource(sourceId: Long): Flow<List<OnlineComicEntity>>

    @Query("SELECT * FROM online_comics WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): OnlineComicEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(comics: List<OnlineComicEntity>): List<Long>

    @Query("DELETE FROM online_comics WHERE sourceId = :sourceId")
    suspend fun deleteBySource(sourceId: Long)
}
