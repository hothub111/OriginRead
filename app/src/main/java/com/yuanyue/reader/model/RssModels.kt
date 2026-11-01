package com.yuanyue.reader.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "rss_feeds")
data class RssFeedEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val url: String,
    val description: String? = null,
    val siteUrl: String? = null,
    val faviconUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
)

@Entity(
    tableName = "rss_articles",
    indices = [Index(value = ["feedId", "guid"], unique = true)],
)
data class RssArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val feedId: Long,
    val guid: String,
    val title: String,
    val link: String? = null,
    val description: String? = null,
    val contentHtml: String? = null,
    val author: String? = null,
    val publishedAt: Long? = null,
    val isRead: Boolean = false,
    val isFavorite: Boolean = false,
)
