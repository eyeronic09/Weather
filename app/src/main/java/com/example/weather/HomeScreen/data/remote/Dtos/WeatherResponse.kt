package com.example.weather.HomeScreen.data.remote.Dtos
import kotlinx.serialization.Serializable



@Serializable
data class WeatherResponse(
    val location: Location,
    val current: Current
)

