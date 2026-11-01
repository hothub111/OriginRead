package com.yuanyue.reader.ui.reader

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext
import kotlin.math.min

/**
 * 把章节正文排版成整页。
 *
 * 关键点：
 * - 排版在 Dispatchers.Default 上执行，主线程只负责渲染，不会再因为大段文本卡死；
 * - 每次测量最多取一页多一点的文本（而不是measure整章/整本书），避免 O(n²) 卡住；
 * - 字号/行距/屏幕尺寸变化时会先做 120ms 防抖，拖动滑块不会疯狂重排。
 */
@Composable
fun rememberPagedText(
    text: String,
    style: TextStyle,
    pageWidth: Int,
    pageHeight: Int,
): List<AnnotatedString>? {
    val textMeasurer = rememberTextMeasurer()
    var pages by remember(text, style, pageWidth, pageHeight) {
        mutableStateOf<List<AnnotatedString>?>(null)
    }

    LaunchedEffect(text, style, pageWidth, pageHeight) {
        if (text.isBlank() || pageWidth <= 0 || pageHeight <= 0) {
            pages = emptyList()
            return@LaunchedEffect
        }
        delay(120)
        pages = withContext(Dispatchers.Default) {
            paginate(text, style, pageWidth, pageHeight, textMeasurer)
        }
    }

    return pages
}

private suspend fun paginate(
    text: String,
    style: TextStyle,
    pageWidth: Int,
    pageHeight: Int,
    textMeasurer: TextMeasurer,
): List<AnnotatedString> {
    val normalized = text
        .replace("\r\n", "\n")
        .replace('\r', '\n')
    val paragraphs = normalized.split('\n')
        .map { it.trim() }
        .filter { it.isNotEmpty() }

    if (paragraphs.isEmpty()) return emptyList()

    val pages = mutableListOf<AnnotatedString>()
    val current = StringBuilder()

    fun flush() {
        if (current.isNotBlank()) {
            pages.add(AnnotatedString(current.toString().trimEnd()))
            current.clear()
        }
    }

    suspend fun measure(value: String): TextLayoutResult {
        coroutineContext.ensureActive()
        return textMeasurer.measure(
            text = AnnotatedString(value),
            style = style,
            overflow = TextOverflow.Visible,
            softWrap = true,
            maxLines = Int.MAX_VALUE,
            constraints = Constraints(maxWidth = pageWidth),
        )
    }

    suspend fun takePage(remaining: String): Pair<String, String> {
        var take = min(remaining.length, 1_500)
        while (true) {
            val slice = remaining.substring(0, take)
            val layout = measure(slice)
            val fitsWholeSlice = layout.size.height <= pageHeight
            if (fitsWholeSlice && take >= remaining.length) {
                val piece = slice.trimEnd()
                return piece to ""
            }
            if (fitsWholeSlice) {
                if (take >= remaining.length) {
                    return slice.trimEnd() to ""
                }
                take = min(remaining.length, take * 2)
                continue
            }

            var fitLines = layout.lineCount
            while (fitLines > 0 && layout.getLineBottom(fitLines - 1) > pageHeight) {
                fitLines--
            }
            if (fitLines <= 0) fitLines = 1
            val end = layout.getLineEnd(fitLines - 1, visibleEnd = true)
                .coerceIn(1, slice.length)
            val piece = slice.substring(0, end).trimEnd()
            val rest = remaining.substring(end).trimStart()
            return piece to rest
        }
    }

    for (paragraph in paragraphs) {
        var remaining = paragraph
        while (remaining.isNotEmpty()) {
            if (remaining.length <= 2_000) {
                if (current.isEmpty()) {
                    if (measure(remaining).size.height <= pageHeight) {
                        current.append(remaining)
                        break
                    }
                } else {
                    val candidate = current.toString() + "\n" + remaining
                    if (measure(candidate).size.height <= pageHeight) {
                        current.append('\n').append(remaining)
                        break
                    }
                    flush()
                    continue
                }
            }

            flush()
            val (piece, rest) = takePage(remaining)
            if (piece.isEmpty()) break
            pages.add(AnnotatedString(piece))
            if (rest == remaining) break
            remaining = rest
        }
        coroutineContext.ensureActive()
    }
    flush()

    return pages.ifEmpty {
        if (text.isBlank()) emptyList() else listOf(AnnotatedString(text.take(4_000)))
    }
}
