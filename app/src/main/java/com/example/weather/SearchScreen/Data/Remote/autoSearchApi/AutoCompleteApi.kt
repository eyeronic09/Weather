package com.example.weather.SearchScreen.Data.Remote.autoSearchApi

import com.example.weather.SearchScreen.Data.Remote.Dto.AutoCompleteDto

sealed interface AutoCompleteApi {
    suspend fun searchLocation(searchLocation : String) : List<AutoCompleteDto>
}