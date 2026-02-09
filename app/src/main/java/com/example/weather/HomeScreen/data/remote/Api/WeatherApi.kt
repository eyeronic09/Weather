package com.example.weather.HomeScreen.data.remote.Api

import com.example.weather.HomeScreen.data.remote.Dtos.WeatherResponse

sealed interface WeatherApi {
    suspend fun getWeatherApi(city : String) : WeatherResponse
}