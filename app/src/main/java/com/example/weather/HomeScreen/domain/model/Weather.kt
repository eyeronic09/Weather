package com.example.weather.domain.model

data class Weather(
    val cityName: String,
    val region: String = "",
    val country: String = "",
    val temperatureC: Double,
    val temperatureF: Double,
    val isDay: Boolean,
    val conditionText: String,
    val conditionIconUrl: String,
    val windMph: Double,
    val windKph: Double,
    val windDegree: Int,
    val windDir: String,
    val pressureMb: Double,
    val pressureIn: Double,
    val precipMm: Double,
    val precipIn: Double,
    val humidity: Long,
    val cloud: Int,
    val feelsLikeC: Double,
    val feelsLikeF: Double,
    val windChillC: Double,
    val windChillF: Double,
    val heatIndexC: Double,
    val heatIndexF: Double,
    val dewPointC: Double,
    val dewPointF: Double,
    val visKm: Double,
    val visMiles: Double,
    val uv: Double,
    val gustMph: Double,
    val gustKph: Double,
    val airQuality: Int,
    val forecastDays: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val hourlyForecasts: List<HourlyForecast>
)

data class HourlyForecast(
    val time: String = "",
    val tempC: Float = 0f,
    val tempF: Float = 0f,
    val icon: String = "",
    val conditionText: String = ""
)
