package com.example.weather.HomeScreen.data.remote.Api

import com.example.weather.HomeScreen.data.remote.Dtos.CurrentWeatherDto

sealed interface WeatherApi {
    suspend fun getWeatherApi(city : String) : CurrentWeatherDto
}