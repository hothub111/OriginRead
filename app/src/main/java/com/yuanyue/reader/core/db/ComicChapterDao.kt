package com.yuanyue.reader.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuanyue.reader.model.ComicChapterEntity

@Dao
interface ComicChapterDao {
    @Query("SELECT * FROM comic_chapters WHERE comicId = :comicId ORDER BY chapterIndex ASC, id ASC")
    suspend fun getByComic(comicId: Long): List<ComicChapterEntity>

    @Query("SELECT * FROM comic_chapters WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): ComicChapterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(chapters: List<ComicChapterEntity>): List<Long>

    @Query("DELETE FROM comic_chapters WHERE comicId = :comicId")
    suspend fun deleteByComic(comicId: Long)
}
