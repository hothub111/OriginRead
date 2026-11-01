package com.yuanyue.reader.ui.library

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuanyue.reader.data.LibraryRepository
import com.yuanyue.reader.data.OperationResult
import com.yuanyue.reader.model.BookEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class LibraryViewModel(
    private val libraryRepository: LibraryRepository,
) : ViewModel() {
    var isImporting by mutableStateOf(false)
        private set

    val books = libraryRepository.books.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    suspend fun importBook(uri: Uri, name: String? = null): OperationResult<Long> {
        if (isImporting) return OperationResult.Error("正在导入中")
        isImporting = true
        return try {
            libraryRepository.importBook(uri, name)
        } finally {
            isImporting = false
        }
    }

    suspend fun deleteBook(book: BookEntity) = libraryRepository.deleteBook(book)
}
