package com.example.weather.HomeScreen.data.remote.Repository

import android.util.Log
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

    companion object {
        private const val TAG = "WeatherRepository"
    }

    override suspend fun getCurrentWeather(city: String): Result<Weather , WeatherError> {
        return try {
            Log.d(TAG, "Making API call for city: $city")
            val response = api.getWeatherApi(city)
            Log.d(TAG, "API call successful for city: $city")
            response.toDomain()?.let { weather ->
                Log.d(TAG, "Successfully mapped weather data for city: $city, temp: ${weather.temperatureC}°C")
                Result.Success(weather)
            } ?: run {
                Log.e(TAG, "Failed to map weather data for city: $city, error: ${response.error?.message}")
                Result.Error(
                    errorMessage = WeatherError.ApiError(response.error?.message ?: "Invalid API response")
                )
            }
        } catch (e: ClientRequestException) {
            Log.e(TAG, "Client request exception for city: $city", e)
            Result.Error(mapToWeatherError(e))
        } catch (e: Exception) {
            Log.e(TAG, "Unexpected error for city: $city", e)
            Result.Error(WeatherError.Unknown)
        }
    }
}
