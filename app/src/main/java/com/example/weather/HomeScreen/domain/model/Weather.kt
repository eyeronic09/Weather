package com.example.weather.domain.model

data class Weather(
    val cityName: String,
    val region: String = "",
    val country: String = "",
    val temperatureC: Double,
    val temperatureF: Double,
    val conditionText: String,
    val conditionIconUrl: String,
    val windKph: Any,
    val humidity: Any,
    val forecastDays: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val hourlyForecasts: List<HourlyForecast>
)

data class HourlyForecast(
    val time: String = "",
    val tempC: Float = 0f,
    val tempF: Float = 0f,
    val icon: String = "",
    val conditionText: String = ""
)