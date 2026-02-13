package com.example.weather.HomeScreen.domain.model

import androidx.compose.runtime.Composable



data class Weather(
    val cityName: String,
    val region: String,
    val country: String,
    val temperatureC: Float,
    val conditionText: String,
    val conditionIconUrl: String,
    val windKph: Float,
    val humidity: Int,
    val hourlyForecast: List<HourlyWeather>
)

data class HourlyWeather(
    val temperatureC: Float,
    val time: String,
    val conditionIconUrl: String,
    val conditionText: String
)