package com.example.weather.HomeScreen.data.remote.Dtos

import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    val text: String,
    val icon: String   // "//cdn.weatherapi.com/weather/64x64/day/xxx.png"
)