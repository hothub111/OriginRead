package com.yuanyue.reader.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuanyue.reader.model.OnlineChapterEntity

@Dao
interface OnlineChapterDao {
    @Query("SELECT * FROM online_chapters WHERE bookId = :bookId ORDER BY chapterIndex ASC, id ASC")
    suspend fun getByBook(bookId: Long): List<OnlineChapterEntity>

    @Query("SELECT * FROM online_chapters WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): OnlineChapterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(chapters: List<OnlineChapterEntity>): List<Long>

    @Query("DELETE FROM online_chapters WHERE bookId = :bookId")
    suspend fun deleteByBook(bookId: Long)
}
