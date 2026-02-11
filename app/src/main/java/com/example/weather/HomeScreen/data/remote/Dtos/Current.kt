package com.example.weather.HomeScreen.data.remote.Dtos

import kotlinx.serialization.Serializable

@Serializable
data class Current(
    val temp_c: Int,
    val condition: Condition,
    val wind_kph: Float,
    val humidity: Int
)