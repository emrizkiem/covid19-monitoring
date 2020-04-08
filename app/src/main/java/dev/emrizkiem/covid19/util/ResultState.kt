package dev.emrizkiem.covid19.util

sealed class ResultState<out T: Any> {
    data class Error(val error: String): ResultState<Nothing>()
    data class Message(val message: String): ResultState<String>()
    data class Success<out T: Any>(val data: T?): ResultState<T>()
}