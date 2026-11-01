package com.yuanyue.reader.core.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuanyue.reader.model.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM books ORDER BY addedAt DESC")
    fun observeAll(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): BookEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: BookEntity): Long

    @Query(
        "UPDATE books SET lastReadPosition = :chapterIndex, totalPages = :pageIndex, " +
            "progressPercent = :progress WHERE id = :bookId",
    )
    suspend fun updateProgress(
        bookId: Long,
        chapterIndex: Int,
        pageIndex: Int,
        progress: Float,
    )

    @Delete
    suspend fun delete(book: BookEntity)
}
