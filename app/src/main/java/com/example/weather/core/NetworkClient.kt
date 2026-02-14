package com.example.weather.core

import android.util.Log
import com.example.weather.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
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
        
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("KtorClient", message)
                }
            }
            level = LogLevel.ALL
        }
        defaultRequest {
            url {
                parameters.append("key" , apiKey)
                Log.d("NetworkClient", "Adding API key to default request: $apiKey")

            }
        }

    }
}