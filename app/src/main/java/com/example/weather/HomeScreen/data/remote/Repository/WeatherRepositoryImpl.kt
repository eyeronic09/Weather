package com.example.weather.HomeScreen.data.remote.Repository

import android.util.Log
import com.example.weather.HomeScreen.data.remote.Api.WeatherApi
import com.example.weather.HomeScreen.data.remote.Mapper.Mapper
import com.example.weather.HomeScreen.data.remote.Mapper.Result
import com.example.weather.HomeScreen.data.remote.Mapper.mapToWeatherError
import com.example.weather.HomeScreen.domain.model.WeatherError
import com.example.weather.HomeScreen.domain.repository.WeatherRepository
import com.example.weather.domain.model.Weather
import io.ktor.client.plugins.ClientRequestException

class WeatherRepositoryImpl(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getCurrentWeather(city: String): Result<Weather, WeatherError> {
        return try {
            val response = api.getWeatherApi(city)

            if (response.error != null) {
                return Result.Error(errorMessage = WeatherError.ApiError(response.error.message))
            }

            Log.d("WeatherResponse", "Location: ${response.location}")
            Log.d("WeatherResponse", "Current: ${response.current}")
            Log.d("WeatherResponse", "Full response: $response")
            
            if (response.location == null || response.current == null) {
                return Result.Error(errorMessage = WeatherError.Unknown)
            }

            return Result.Success(
                data = Mapper.CurrentWeatherDtoToDomain(response)
            )
        } catch (e: ClientRequestException) {
            Result.Error(errorMessage = mapToWeatherError(e))
        } catch (e: Exception) {
            Result.Error(errorMessage = mapToWeatherError(e))
        }
    }
}
