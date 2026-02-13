package com.example.weather.HomeScreen.data.remote.Api

import com.example.weather.BuildConfig
import com.example.weather.HomeScreen.data.remote.Dtos.WeatherResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WeatherApiImpl(private val client: HttpClient) : WeatherApi {

    private val api = BuildConfig.Weather_API_KEY
    private val base_url = "https://api.weatherapi.com/v1"

    override suspend fun getWeatherApi(city: String): WeatherResponseDto {
        return client.get(urlString = "$base_url/current.json") {
            parameter("key", api)
            parameter("q", city)
            parameter("aqi", "no")
        }.body()
    }
}
