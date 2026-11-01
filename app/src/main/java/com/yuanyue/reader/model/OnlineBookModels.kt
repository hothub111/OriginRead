package com.yuanyue.reader.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "book_sources")
data class BookSourceEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val url: String,
    val searchUrl: String? = null,
    val searchListRule: String? = null,
    val bookNameRule: String? = null,
    val bookAuthorRule: String? = null,
    val bookCoverRule: String? = null,
    val bookUrlRule: String? = null,
    val introRule: String? = null,
    val coverRule: String? = null,
    val tocUrlRule: String? = null,
    val chapterListRule: String? = null,
    val chapterNameRule: String? = null,
    val chapterUrlRule: String? = null,
    val contentRule: String? = null,
    val contentReplaceRules: String? = null,
    val enabled: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
)

@Entity(
    tableName = "online_books",
    indices = [Index(value = ["sourceId", "bookUrl"], unique = true)],
)
data class OnlineBookEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val sourceId: Long,
    val title: String,
    val author: String? = null,
    val coverUrl: String? = null,
    val bookUrl: String,
    val intro: String? = null,
    val latestChapter: String? = null,
    val cachedAt: Long = System.currentTimeMillis(),
)

@Entity(
    tableName = "online_chapters",
    indices = [Index(value = ["bookId", "url"], unique = true)],
)
data class OnlineChapterEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val bookId: Long,
    val sourceId: Long,
    val title: String,
    val url: String,
    val chapterIndex: Int = 0,
)

@Entity(tableName = "cached_chapter_content")
data class CachedChapterContentEntity(
    @PrimaryKey val chapterId: Long,
    val content: String,
    val cachedAt: Long = System.currentTimeMillis(),
)
