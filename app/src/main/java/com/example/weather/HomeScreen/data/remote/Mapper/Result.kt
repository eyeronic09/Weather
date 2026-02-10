package com.example.weather.HomeScreen.data.remote.Mapper

sealed class Result <out T , out E> {
    data class Success<out T>(val data : T) : Result<T , Nothing>()
    data class Error<out E>(val data : E) : Result<Nothing , E>()
}