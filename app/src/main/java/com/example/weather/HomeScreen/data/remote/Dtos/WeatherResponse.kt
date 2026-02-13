package com.example.weather.HomeScreen.data.remote.Dtos
import kotlinx.serialization.Serializable





import kotlinx.serialization.SerialName

@Serializable
data class WeatherResponseDto(
    val location: LocationDto? = null,

    @SerialName("current")
    val currentWeather: CurrentWeatherDto? = null,

    val forecast: ForecastDto? = null,
    val error: ErrorDto? = null
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

@Serializable
data class ErrorDto(
    val code: Int,
    val message: String
)

@Serializable
data class ForecastDto(
    @SerialName("forecastday")
    val forecastDay: List<ForecastDayDto>
)

@Serializable
data class ForecastDayDto(
    val date: String,
    @SerialName("date_epoch")
    val dateEpoch: Long,
    val day: DayDto, // ← This is the daily summary
    val astro: AstroDto,
    val hour: List<HourlyDto>// ← This is hourly breakdown
)

@Serializable
data class DayDto(
    @SerialName("maxtemp_c")
    val maxTempC: Float,
    @SerialName("maxtemp_f")
    val maxTempF: Float,
    @SerialName("mintemp_c")
    val minTempC: Float,
    @SerialName("mintemp_f")
    val minTempF: Float,
    @SerialName("avgtemp_c")
    val avgTempC: Float,
    @SerialName("avgtemp_f")
    val avgTempF: Float,
    @SerialName("maxwind_mph")
    val maxWindMph: Float,
    @SerialName("maxwind_kph")
    val maxWindKph: Float,
    @SerialName("totalprecip_mm")
    val totalPrecipMm: Float,
    @SerialName("totalprecip_in")
    val totalPrecipIn: Float,
    @SerialName("totalsnow_cm")
    val totalSnowCm: Float,
    @SerialName("avgvis_km")
    val avgVisKm: Float,
    @SerialName("avgvis_miles")
    val avgVisMiles: Float,
    @SerialName("avghumidity")
    val avgHumidity: Int,
    @SerialName("daily_will_it_rain")
    val dailyWillItRain: Int,
    @SerialName("daily_chance_of_rain")
    val dailyChanceOfRain: Int,
    @SerialName("daily_will_it_snow")
    val dailyWillItSnow: Int,
    @SerialName("daily_chance_of_snow")
    val dailyChanceOfSnow: Int,
    val condition: ConditionDto,
    val uv: Float
)

@Serializable
data class AstroDto(
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
    val moonset: String,
    @SerialName("moon_phase")
    val moonPhase: String,
    @SerialName("moon_illumination")
    val moonIllumination: Int,
    @SerialName("is_moon_up")
    val isMoonUp: Int,
    @SerialName("is_sun_up")
    val isSunUp: Int
)

@Serializable
data class HourlyDto(
    @SerialName("temp_c")
    val tempC: Float,
    val time: String,
    val condition: ConditionDto
)
