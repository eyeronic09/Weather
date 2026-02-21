package com.example.weather.SearchScreen.Domain.Model

sealed class AutoSearchError {
    data object Unauthorized : AutoSearchError()
    data object NotFound : AutoSearchError()
    data object Server : AutoSearchError()
    data object Network : AutoSearchError()
    data class ApiError(val message: String) : AutoSearchError()
    data object Unknown : AutoSearchError()
}