package com.example.weather.HomeScreen.domain.repository

import com.example.weather.HomeScreen.data.remote.Mapper.Result
import com.example.weather.HomeScreen.domain.model.Weather
import com.example.weather.HomeScreen.domain.model.WeatherError

interface WeatherRepository {
    suspend fun getCurrentWeather(city : String) : Result<Weather , WeatherError>
}