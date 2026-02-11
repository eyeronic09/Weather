package com.example.weather.HomeScreen.domain.model




data class Weather(
    val cityName: String,
    val region: String = "",
    val country: String = "",
    val localtimeEpoch: Long = 0L,
    val temperatureC: Int,
    val conditionText: String,
    val conditionIconUrl: String,
    val windKph: Float,
    val humidity: Int
)