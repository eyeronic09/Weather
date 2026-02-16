package com.example.weather.HomeScreen.data.remote.Api

import android.util.Log
import com.example.weather.BuildConfig
import com.example.weather.HomeScreen.data.remote.Dtos.CurrentWeatherDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WeatherApiImpl(private val client: HttpClient) : WeatherApi {

    private val api = BuildConfig.WEATHER_API_KEY
    private val base_url = "https://api.weatherapi.com/v1"

    override suspend fun getWeatherApi(city: String): CurrentWeatherDto{
        Log.d("WeatherApi", "API Key: ${if (api.isNotEmpty()) "Present" else "MISSING"}")
        Log.d("WeatherApi", "Making request for city: $city")
        
        return try {
            val response = client.get(urlString = "$base_url/forecast.json") {
                parameter("key", api)
                parameter("q", city)
                parameter("aqi", "no")
            }
            Log.d("WeatherApi", "Response received: $response")
            response.body()

        } catch (e: Exception) {
            Log.e("WeatherApi", "API call failed", e)
            throw e
        }
    }
}
