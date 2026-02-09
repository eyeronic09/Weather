package com.example.weather.HomeScreen.data.remote.Repository

import com.example.weather.HomeScreen.data.remote.Api.WeatherApi
import com.example.weather.HomeScreen.data.remote.Mapper.toDomain
import com.example.weather.HomeScreen.domain.model.Weather
import com.example.weather.HomeScreen.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getCurrentWeather(city: String): Result<Weather> {
        return try {
            val response = api.getWeatherApi(city)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
