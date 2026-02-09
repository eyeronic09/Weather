package com.example.weather.HomeScreen.data.remote.Dtos
import kotlinx.serialization.Serializable



@Serializable
data class WeatherResponse(
    val location: Location,
    val current: Current
)

@Serializable
data class Location(
    val name: String,
    val region: String,
    val country: String,
    val localtime: String
)

@Serializable
data class Current(
    val temp_c: Float,
    val condition: Condition,
    val wind_kph: Float,
    val humidity: Int
)

@Serializable
data class Condition(
    val text: String,
    val icon: String   // "//cdn.weatherapi.com/weather/64x64/day/xxx.png"
)