package com.yuanyue.reader.data

import com.yuanyue.reader.model.ComicSourceEntity
import org.json.JSONObject

object ComicSourceImporter {
    fun fromJson(json: String): ComicSourceEntity {
        val root = JSONObject(json)
        val name = firstString(root, "comicSourceName", "name")
            ?: throw IllegalArgumentException("缺少漫画源名称")
        val url = firstString(root, "comicSourceUrl", "url")
            ?: throw IllegalArgumentException("缺少漫画源地址")
        return ComicSourceEntity(
            name = name,
            url = url,
            searchUrl = firstString(root, "searchUrl"),
            searchListRule = firstString(root, "comicList", "searchList"),
            comicTitleRule = firstString(root, "comicTitle", "title"),
            comicUrlRule = firstString(root, "comicUrl", "detailUrl"),
            comicCoverRule = firstString(root, "comicCover", "cover"),
            chapterListRule = firstString(root, "chapterList"),
            chapterNameRule = firstString(root, "chapterName"),
            chapterUrlRule = firstString(root, "chapterUrl"),
            imageListRule = firstString(root, "imageList", "images"),
            imageUrlRule = firstString(root, "imageUrl", "image"),
        )
    }

    private fun firstString(root: JSONObject, vararg keys: String): String? {
        keys.forEach { key ->
            val value = root.optString(key)
            if (value.isNotBlank()) return value
        }
        return null
    }
}
