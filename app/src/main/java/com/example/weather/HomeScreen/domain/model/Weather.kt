package com.example.weather.domain.model

data class Weather(
    val cityName: String,
    val region: String = "",
    val country: String = "",
    val temperatureC: Any,
    val temperatureF: Any,
    val conditionText: String,
    val conditionIconUrl: String,
    val windKph: Any,
    val humidity: Any,
    val forcastday : List<HourlyItem>

)

data class HourlyItem(
    val time: String = "",
    val tempC: Float = 0f,
    val tempF : Float = 0f,
    val icon : String = "",
    val conditionText: String = ""
)
