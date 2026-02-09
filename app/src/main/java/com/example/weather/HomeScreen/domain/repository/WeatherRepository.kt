package com.example.weather.HomeScreen.domain.repository

import com.example.weather.HomeScreen.domain.model.Weather

interface WeatherRepository {
    suspend fun getCurrentWeather(city : String) : Result<Weather>
}