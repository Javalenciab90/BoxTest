package com.java90.movilboxtest.vo

// exposing network status
sealed class Resource<out T> {
        data class Success<out T>(val data: T) : Resource<T>()
        data class Failure<out T>(val exception: Exception) : Resource<T>()
        class Loading<out T> : Resource<T>()
}
