package com.example.weather.SearchScreen.Data.Remote.autoSearchApi

import com.example.weather.BuildConfig
import com.example.weather.SearchScreen.Data.Remote.Dto.AutoCompleteDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter


class AutoSearchImpl(private val client : HttpClient) : AutoCompleteApi{
    private val api = BuildConfig.WEATHER_API_KEY
    private val base_url = "https://api.weatherapi.com/v1/search.json"

    override suspend fun searchLocation(searchLocation: String): List<AutoCompleteDto> {


         val response = client.get(base_url) {
             parameter("key" , api)
             parameter("q" , searchLocation)
         }

        return response.body()
    }

}
