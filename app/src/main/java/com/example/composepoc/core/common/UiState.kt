package com.example.composepoc.core.common

/**
 * It's a sealed class which provides the various UI states &
 * the corresponding data, flag, error emitted by the Coroutine's Flow
 */
sealed class UiState<T>(val data: T?, val message: String? = null) {
    class Success<T>(data: T?) : UiState<T>(data)
    class Loading<T>(data: T? = null) : UiState<T>(data)
    class Error<T>(data: T? = null, message: String?) : UiState<T>(data, message)
}