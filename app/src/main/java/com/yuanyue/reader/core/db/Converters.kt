package com.yuanyue.reader.core.db

import androidx.room.TypeConverter
import com.yuanyue.reader.model.BookFormat

class Converters {
    @TypeConverter
    fun fromBookFormat(format: BookFormat): String = format.name

    @TypeConverter
    fun toBookFormat(value: String): BookFormat = BookFormat.valueOf(value)
}
