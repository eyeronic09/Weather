package com.example.weather.HomeScreen.data.remote.Dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDto(
    @SerialName("forecastday")
    val forecastday: List<Forecastday>,
)

@Serializable
data class Forecastday(
    val date: String,
    @SerialName("date_epoch")
    val dateEpoch: Long,
    val hour: List<Hour>,
)

@Serializable
data class Hour(
    @SerialName("time_epoch")
    val timeEpoch: Long,
    val time: String,
    @SerialName("temp_c")
    val tempC: Double,
    @SerialName("temp_f")
    val tempF: Double,
    @SerialName("is_day")
    val isDay: Long,
    @SerialName("condition")
    val condition: Condition,
    @SerialName("wind_mph")
    val windMph: Double,
)