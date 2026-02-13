package com.example.weather.HomeScreen.data.remote.Api

import com.example.weather.HomeScreen.data.remote.Dtos.WeatherResponseDto

sealed interface WeatherApi {
    suspend fun getWeatherApi(city : String) : WeatherResponseDto
}