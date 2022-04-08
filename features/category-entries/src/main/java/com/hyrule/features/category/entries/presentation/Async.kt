package com.hyrule.features.category.entries.presentation

sealed class Async<out T> {
    object Loading : Async<Nothing>()
    data class Error(val message: String?) : Async<Nothing>()
    data class Success<T>(val value: T) : Async<T>()
}
