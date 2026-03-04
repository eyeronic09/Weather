package com.example.weather.HomeScreen.UI_Presentation


import com.example.weather.domain.model.HourlyForecast
import com.example.weather.domain.model.Weather

data class WeatherState(
    val isLoading: Boolean = false,
    val isTempC : Boolean = true,
    val currentWeather: Weather? = null,
    val hourlyWeather: List<HourlyForecast>? = null,
    val error: String? = null,
    val searchWeatherCity : String = ""
)
