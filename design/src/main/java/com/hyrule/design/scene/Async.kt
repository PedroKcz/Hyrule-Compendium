package com.hyrule.design.scene

sealed class Async<out T> {
    object Loading : Async<Nothing>()
    data class Error(val message: String?) : Async<Nothing>()
    data class Success<T>(val value: T) : Async<T>()
}
