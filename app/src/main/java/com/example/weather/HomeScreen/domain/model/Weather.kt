package com.example.weather.domain.model

data class Weather(
    val cityName: String,
    val region: String = "",
    val country: String = "",
    val temperatureC: Float,
    val conditionText: String,
    val conditionIconUrl: String,
    val windKph: Float,
    val humidity: Long,
    val hourly: List<HourlyItem>
)

data class HourlyItem(
    val time: String,
    val tempC: Float,
    val conditionText: String
)
