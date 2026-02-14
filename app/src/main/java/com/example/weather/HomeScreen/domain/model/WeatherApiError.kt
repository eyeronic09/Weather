package com.example.weather.HomeScreen.domain.model

sealed class WeatherError {
    data object Unauthorized : WeatherError()
    data object NotFound : WeatherError()
    data object Server : WeatherError()
    data object Network : WeatherError()
    data class ApiError(val message: String) : WeatherError()
    data object Unknown : WeatherError()
    data class ApiError(val message: String) : WeatherError()
}