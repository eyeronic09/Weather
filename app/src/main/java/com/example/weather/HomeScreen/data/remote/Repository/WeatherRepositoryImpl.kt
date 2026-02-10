package com.example.weather.HomeScreen.data.remote.Repository

import com.example.weather.HomeScreen.data.remote.Api.WeatherApi
import com.example.weather.HomeScreen.data.remote.Mapper.Result
import com.example.weather.HomeScreen.data.remote.Mapper.mapToWeatherError
import com.example.weather.HomeScreen.data.remote.Mapper.toDomain
import com.example.weather.HomeScreen.domain.model.Weather
import com.example.weather.HomeScreen.domain.model.WeatherError
import com.example.weather.HomeScreen.domain.repository.WeatherRepository
import io.ktor.client.plugins.ClientRequestException

class WeatherRepositoryImpl(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getCurrentWeather(city: String): Result<Weather , WeatherError> {
        return try {
            val response = api.getWeatherApi(city)
            Result.Success(response.toDomain())
        } catch (e: ClientRequestException) {
            Result.Error(mapToWeatherError(e))
        }
    }
}
