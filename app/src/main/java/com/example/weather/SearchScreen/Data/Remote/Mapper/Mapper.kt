package com.example.weather.SearchScreen.Data.Remote.Mapper

import com.example.weather.HomeScreen.domain.model.WeatherError
import com.example.weather.SearchScreen.Data.Remote.Dto.AutoCompleteDto
import com.example.weather.SearchScreen.Domain.Model.AutoComplete
import com.example.weather.SearchScreen.Domain.Model.AutoSearchError
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import okio.IOException

object Mapper {
    fun AutoCompleteDtoToDomain(dto : AutoCompleteDto): AutoComplete {
        return AutoComplete(
            name = dto.name,
            region = dto.region,
            country = dto.country
        )
    }

    fun AutoErrorToDomain(e: Throwable) : AutoSearchError{
        return when (e) {
            is ClientRequestException -> {
                when (e.response.status.value) {
                    401 -> AutoSearchError.Unauthorized
                    404 -> AutoSearchError.NotFound
                    else -> AutoSearchError.Unknown
                }
            }
            is ServerResponseException -> AutoSearchError.Server
            is IOException -> AutoSearchError.Network
            else -> AutoSearchError.Unknown
        }
    }
}