package com.yuanyue.reader.core.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuanyue.reader.model.BookSourceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookSourceDao {
    @Query("SELECT * FROM book_sources ORDER BY createdAt ASC")
    fun observeAll(): Flow<List<BookSourceEntity>>

    @Query("SELECT * FROM book_sources WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): BookSourceEntity?

    @Query("SELECT * FROM book_sources WHERE url = :url LIMIT 1")
    suspend fun getByUrl(url: String): BookSourceEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(source: BookSourceEntity): Long

    @Delete
    suspend fun delete(source: BookSourceEntity)
}
