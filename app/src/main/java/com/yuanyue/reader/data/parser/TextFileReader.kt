package com.yuanyue.reader.data.parser

import java.io.File
import java.nio.ByteBuffer
import java.nio.charset.CharacterCodingException
import java.nio.charset.Charset
import java.nio.charset.CodingErrorAction
import java.nio.charset.StandardCharsets

object TextFileReader {
    fun readSmart(file: File): String {
        val bytes = file.readBytes()
        if (bytes.isEmpty()) return ""
        val start = if (bytes.size >= 3 && bytes[0] == 0xEF.toByte() && bytes[1] == 0xBB.toByte() && bytes[2] == 0xBF.toByte()) 3 else 0
        return if (isValidUtf8(bytes, start)) {
            String(bytes, start, bytes.size - start, StandardCharsets.UTF_8)
        } else {
            runCatching {
                String(bytes, start, bytes.size - start, Charset.forName("GBK"))
            }.getOrElse {
                String(bytes, start, bytes.size - start, StandardCharsets.UTF_8)
            }
        }
    }

    private fun isValidUtf8(bytes: ByteArray, start: Int): Boolean {
        return try {
            val decoder = StandardCharsets.UTF_8.newDecoder()
                .onMalformedInput(CodingErrorAction.REPORT)
                .onUnmappableCharacter(CodingErrorAction.REPORT)
            decoder.decode(ByteBuffer.wrap(bytes, start, bytes.size - start))
            true
        } catch (_: CharacterCodingException) {
            false
        }
    }
}
