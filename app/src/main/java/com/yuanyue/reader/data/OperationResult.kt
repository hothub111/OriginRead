package com.yuanyue.reader.data

sealed interface OperationResult<out T> {
    data class Success<T>(val data: T) : OperationResult<T>
    data class Error(val message: String) : OperationResult<Nothing>
}
