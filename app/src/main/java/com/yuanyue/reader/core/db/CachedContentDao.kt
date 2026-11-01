package com.yuanyue.reader.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuanyue.reader.model.CachedChapterContentEntity

@Dao
interface CachedContentDao {
    @Query("SELECT content FROM cached_chapter_content WHERE chapterId = :chapterId LIMIT 1")
    suspend fun get(chapterId: Long): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(content: CachedChapterContentEntity)

    @Query("DELETE FROM cached_chapter_content WHERE chapterId IN (:chapterIds)")
    suspend fun deleteByChapters(chapterIds: List<Long>)
}
