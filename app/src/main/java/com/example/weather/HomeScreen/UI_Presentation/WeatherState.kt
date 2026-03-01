package com.example.weather.HomeScreen.UI_Presentation


import com.example.weather.domain.model.HourlyItem
import com.example.weather.domain.model.Weather

data class WeatherState(
    val isLoading: Boolean = false,
    val isTempC : Boolean = true,
    val currentWeather: Weather? = null,
    val hourlyWeather: List<HourlyItem>? = null,
    val error: String? = null,
    val searchWeatherCity : String = ""
)
