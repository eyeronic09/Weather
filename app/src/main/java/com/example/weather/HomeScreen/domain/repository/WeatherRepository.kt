package com.example.weather.HomeScreen.domain.repository

import com.example.weather.HomeScreen.data.remote.Mapper.Result
import com.example.weather.HomeScreen.domain.model.WeatherError
import com.example.weather.domain.model.Weather

interface WeatherRepository {
    suspend fun getCurrentWeather(city : String) : Result<Weather, WeatherError>
}