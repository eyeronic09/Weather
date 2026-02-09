package com.example.weather.core

import com.example.weather.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object NetworkClient {

    private val apiKey = BuildConfig.WEATHER_API_KEY
    val client = HttpClient(OkHttp){

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    explicitNulls = true
                }
            )
        }
        defaultRequest {
            url {
                parameters.append("key" , apiKey)
            }
        }

    }
}