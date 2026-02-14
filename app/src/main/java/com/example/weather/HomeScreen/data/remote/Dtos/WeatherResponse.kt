package com.example.weather.HomeScreen.data.remote.Dtos




import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDto(
    @SerialName("location")
    val location: Location? = null,
    @SerialName("current")
    val current: Current? = null,
    @SerialName("forecast")
    val forecast: ForecastDto? = null,
    @SerialName("error")
    val error: Error? = null,
)

@Serializable
data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    @SerialName("tz_id")
    val tzId: String,
    @SerialName("localtime_epoch")
    val localtimeEpoch: Long,
    val localtime: String,
)

@Serializable
data class Current(
    @SerialName("last_updated_epoch")
    val lastUpdatedEpoch: Long,
    @SerialName("last_updated")
    val lastUpdated: String,
    @SerialName("temp_c")
    val tempC: Double,
    @SerialName("temp_f")
    val tempF: Double,
    @SerialName("humidity")
    val humidity: Long,
    @SerialName("condition")
    val condition: Condition,
    @SerialName("wind_kph")
    val windKph: Double,
)

@Serializable
data class Condition(
    val text: String,
    val icon: String,
    val code: Long,
)

@Serializable
data class Error(
    val code: Long,
    val message: String,
)