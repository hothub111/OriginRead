package com.yuanyue.reader.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "comic_sources")
data class ComicSourceEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val url: String,
    val searchUrl: String? = null,
    val searchListRule: String? = null,
    val comicTitleRule: String? = null,
    val comicUrlRule: String? = null,
    val comicCoverRule: String? = null,
    val chapterListRule: String? = null,
    val chapterNameRule: String? = null,
    val chapterUrlRule: String? = null,
    val imageListRule: String? = null,
    val imageUrlRule: String? = null,
    val enabled: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
)

@Entity(
    tableName = "online_comics",
    indices = [Index(value = ["sourceId", "comicUrl"], unique = true)],
)
data class OnlineComicEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val sourceId: Long,
    val title: String,
    val author: String? = null,
    val coverUrl: String? = null,
    val comicUrl: String,
    val intro: String? = null,
    val cachedAt: Long = System.currentTimeMillis(),
)

@Entity(
    tableName = "comic_chapters",
    indices = [Index(value = ["comicId", "url"], unique = true)],
)
data class ComicChapterEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val comicId: Long,
    val sourceId: Long,
    val title: String,
    val url: String,
    val chapterIndex: Int = 0,
)

data class ComicImageItem(
    val url: String,
    val referer: String? = null,
)
