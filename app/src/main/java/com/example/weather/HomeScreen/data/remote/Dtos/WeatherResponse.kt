package com.example.weather.HomeScreen.data.remote.Dtos
import kotlinx.serialization.Serializable





import kotlinx.serialization.SerialName

@Serializable
data class WeatherResponseDto(
    val location: LocationDto,

    @SerialName("current")
    val currentWeather: CurrentWeatherDto
)

@Serializable
data class LocationDto(
    val name: String,
    val region: String,
    val country: String,

    @SerialName("localtime_epoch")
    val localtimeEpoch: Long
)

@Serializable
data class CurrentWeatherDto(
    @SerialName("temp_c")
    val tempC: Float,

    @SerialName("temp_f")
    val tempF: Float,

    val condition: ConditionDto,

    @SerialName("wind_mph")
    val windMph: Float,

    @SerialName("wind_kph")
    val windKph: Float,

    val humidity: Int
)

@Serializable
data class ConditionDto(
    val text: String,
    val icon: String,
    val code: Int
)
