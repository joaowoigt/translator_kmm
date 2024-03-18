package com.example.translator_kmm.core.domain.util

sealed class Resource<T>(val data: T?, val throwable: Throwable? = null) {
    class Success<T>(data: T): Resource<T>(data = data)
    class Error<T>(throwable: Throwable): Resource<T>(data = null, throwable = throwable)
}