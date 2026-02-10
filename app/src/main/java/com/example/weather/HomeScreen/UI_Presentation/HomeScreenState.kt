package com.example.weather.HomeScreen.UI_Presentation

import com.example.weather.HomeScreen.domain.model.Weather

data class HomeScreenState(
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val error: String? = null,
    val searchWeatherCity : String = ""
)
