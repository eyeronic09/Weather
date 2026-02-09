package com.example.weather.HomeScreen.domain.model



data class Weather(
    val location: Location,
    val current : Current
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val localtimeEpoch: Long
)

data class Current(
    val tempC: Float,
    val condition: Condition,
    val windKph: Float,
    val humidity : Int

)
data class Condition(
    val text: String,
    val icon: String   // "//cdn.weatherapi.com/weather/64x64/day/xxx.png"
)