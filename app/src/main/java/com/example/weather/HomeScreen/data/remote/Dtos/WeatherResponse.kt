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
    @SerialName("is_day")
    val isDay: Int,
    @SerialName("condition")
    val condition: Condition,
    @SerialName("wind_mph")
    val windMph: Double,
    @SerialName("wind_kph")
    val windKph: Double,
    @SerialName("wind_degree")
    val windDegree: Int,
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
    @SerialName("humidity")
    val humidity: Long,
    @SerialName("cloud")
    val cloud: Int,
    @SerialName("feelslike_c")
    val feelsLikeC: Double,
    @SerialName("feelslike_f")
    val feelsLikeF: Double,
    @SerialName("windchill_c")
    val windChillC: Double,
    @SerialName("windchill_f")
    val windChillF: Double,
    @SerialName("heatindex_c")
    val heatIndexC: Double,
    @SerialName("heatindex_f")
    val heatIndexF: Double,
    @SerialName("dewpoint_c")
    val dewPointC: Double,
    @SerialName("dewpoint_f")
    val dewPointF: Double,
    @SerialName("vis_km")
    val visKm: Double,
    @SerialName("vis_miles")
    val visMiles: Double,
    @SerialName("uv")
    val uv: Double,
    @SerialName("gust_mph")
    val gustMph: Double,
    @SerialName("gust_kph")
    val gustKph: Double,
    @SerialName("air_quality")
    val airQuality: AirQuality? = null
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

@Serializable
data class AirQuality(
    @SerialName("us-epa-index")
    val usEpaIndex: Int? = null,
    @SerialName("gb-defra-index")
    val gbDefraIndex: Int? = null
)
