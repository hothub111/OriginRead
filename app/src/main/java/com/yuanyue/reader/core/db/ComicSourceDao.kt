package com.yuanyue.reader.core.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuanyue.reader.model.ComicSourceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicSourceDao {
    @Query("SELECT * FROM comic_sources ORDER BY createdAt ASC")
    fun observeAll(): Flow<List<ComicSourceEntity>>

    @Query("SELECT * FROM comic_sources WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): ComicSourceEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(source: ComicSourceEntity): Long

    @Delete
    suspend fun delete(source: ComicSourceEntity)
}
