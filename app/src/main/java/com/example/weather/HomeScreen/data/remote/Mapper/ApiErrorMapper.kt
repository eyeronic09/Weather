package com.example.weather.HomeScreen.data.remote.Mapper

import com.example.weather.HomeScreen.domain.model.WeatherError
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import java.io.IOException

fun mapToWeatherError(e: Throwable) : WeatherError {
    return when (e) {
        is ClientRequestException -> {
            when (e.response.status.value) {
                401 -> WeatherError.Unauthorized
                404 -> WeatherError.NotFound
                else -> WeatherError.Unknown
            }
        }

        is ServerResponseException -> WeatherError.Server
        is IOException -> WeatherError.Network
        else -> WeatherError.Unknown
    }
}