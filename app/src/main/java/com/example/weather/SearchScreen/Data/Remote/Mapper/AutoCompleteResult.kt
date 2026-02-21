package com.example.weather.SearchScreen.Data.Remote.Mapper

sealed class AutoCompleteResult <out T , out E > {
    data class Success<out T>(val data : T) : AutoCompleteResult<T , Nothing>()
    data class Error <out E> (val errorMessage: E) : AutoCompleteResult<Nothing , E>()
}