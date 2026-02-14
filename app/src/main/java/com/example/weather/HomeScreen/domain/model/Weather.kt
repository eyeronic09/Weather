package com.example.weather.HomeScreen.domain.model




data class Weather(
    val cityName: String,
    val region: String = "",
    val country: String = "",
    val temperatureC: Float,
    val conditionText: String,
    val conditionIconUrl: String,
    val windKph: Float,
    val humidity: Long
)