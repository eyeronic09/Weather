package com.example.weather.HomeScreen.data.remote.Api

import android.util.Log
import com.example.weather.BuildConfig
import com.example.weather.HomeScreen.data.remote.Dtos.CurrentWeatherDto
import com.example.weather.core.util.Coordinates
import com.example.weather.core.util.LocationService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WeatherApiImpl(private val client: HttpClient, private val locationService: LocationService) : WeatherApi {

    private val api = BuildConfig.WEATHER_API_KEY
    private val base_url = "https://api.weatherapi.com/v1"



    override suspend fun getWeatherApi(city: String): CurrentWeatherDto {
        val query = city.ifBlank {
            val coords = locationService.getDeviceCoordinates()
            if (coords != null) {
                val latLon = "${coords.latitude},${coords.longitude}"
                Log.d("WeatherApiCord", "Using device coordinates: $latLon")
                latLon
            } else {
                "auto:ip"
            }
        }
        return try {
            val response = client.get(urlString = "$base_url/forecast.json") {
                parameter("key", api)
                parameter("days", 3)
                parameter("q", query )
                parameter("aqi", "yes")
            }
            Log.d("WeatherApi", "Response received: $response")
            response.body()

        } catch (e: Exception) {
            Log.e("WeatherApi", "API call failed", e)
            throw e
        }
    }
}
