package com.yuanyue.reader.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class BookFormat {
    TXT,
    EPUB,
}

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val author: String? = null,
    val localPath: String,
    val fileName: String,
    val format: BookFormat,
    val coverPath: String? = null,
    val lastReadPosition: Int = 0,
    val totalPages: Int = 0,
    val progressPercent: Float = 0f,
    val addedAt: Long = System.currentTimeMillis(),
)

data class BookChapter(
    val title: String,
    val content: String,
)
