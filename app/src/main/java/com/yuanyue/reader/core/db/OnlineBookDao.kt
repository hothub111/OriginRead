package com.yuanyue.reader.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuanyue.reader.model.OnlineBookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OnlineBookDao {
    @Query("SELECT * FROM online_books WHERE sourceId = :sourceId ORDER BY cachedAt DESC")
    fun observeBySource(sourceId: Long): Flow<List<OnlineBookEntity>>

    @Query("SELECT * FROM online_books WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): OnlineBookEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<OnlineBookEntity>): List<Long>

    @Query("DELETE FROM online_books WHERE sourceId = :sourceId")
    suspend fun deleteBySource(sourceId: Long)
}
