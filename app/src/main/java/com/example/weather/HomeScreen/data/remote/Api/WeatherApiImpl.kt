package com.example.weather.HomeScreen.data.remote.Api

import com.example.weather.HomeScreen.data.remote.Dtos.WeatherResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText

class WeatherApiImpl(private val client: HttpClient) : WeatherApi {

    private val base_url = "https://api.weatherapi.com/v1"

    override suspend fun getWeatherApi(city: String): WeatherResponseDto {
        return client.get(urlString = "$base_url/forecast.json") {
            parameter("q" , city)
            parameter("days", 10)
            parameter("aqi" , "yes")
            parameter("alerts" , "yes")
        }.body<WeatherResponseDto>()
    }
}
