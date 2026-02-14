package com.example.weather.HomeScreen.data.remote.Repository

import android.util.Log
import com.example.weather.HomeScreen.data.remote.Api.WeatherApi
import com.example.weather.HomeScreen.data.remote.Mapper.Result
import com.example.weather.HomeScreen.data.remote.Mapper.mapToWeatherError
import com.example.weather.HomeScreen.data.remote.Mapper.toDomain
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
            
            // Check if the response contains an error
            if (response.error != null) {
                return Result.Error(errorMessage = WeatherError.ApiError(response.error.message))
            }
            
            // Check if required data is present
            if (response.location == null || response.current == null) {
                return Result.Error(errorMessage = WeatherError.Unknown)
            }
            Log.d("Tagresponse" , response.toString())
            return Result.Success(data = response.toDomain())
        } catch (e: ClientRequestException) {
            Result.Error(errorMessage = mapToWeatherError(e))
        } catch (e: Exception) {
            Result.Error(errorMessage = mapToWeatherError(e))
        }
    }
}
