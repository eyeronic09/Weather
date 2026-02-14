package com.example.weather.HomeScreen.data.remote.Dtos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponseDto(
    @SerialName("location")
    val location: Location? = null,
    @SerialName("current")
    val current: Current? = null,
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
    @SerialName("is_day")
    val isDay: Long,
    val condition: Condition,
    @SerialName("wind_mph")
    val windMph: Double,
    @SerialName("wind_kph")
    val windKph: Double,
    @SerialName("wind_degree")
    val windDegree: Long,
    @SerialName("wind_dir")
    val windDir: String,
    @SerialName("pressure_mb")
    val pressureMb: Double,
    @SerialName("pressure_in")
    val pressureIn: Double,
    @SerialName("precip_mm")
    val precipMm: Double,
    @SerialName("precip_in")
    val precipIn: Double,
    val humidity: Long,
    val cloud: Long,
    @SerialName("feelslike_c")
    val feelslikeC: Double,
    @SerialName("feelslike_f")
    val feelslikeF: Double,
    @SerialName("windchill_c")
    val windchillC: Double,
    @SerialName("windchill_f")
    val windchillF: Double,
    @SerialName("heatindex_c")
    val heatindexC: Double,
    @SerialName("heatindex_f")
    val heatindexF: Double,
    @SerialName("dewpoint_c")
    val dewpointC: Double,
    @SerialName("dewpoint_f")
    val dewpointF: Double,
    @SerialName("vis_km")
    val visKm: Double,
    @SerialName("vis_miles")
    val visMiles: Double,
    val uv: Double,
    @SerialName("gust_mph")
    val gustMph: Double,
    @SerialName("gust_kph")
    val gustKph: Double,
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