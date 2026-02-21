package com.example.weather.SearchScreen.Data.Remote.Repository

import com.example.weather.SearchScreen.Data.Remote.Mapper.AutoCompleteResult
import com.example.weather.SearchScreen.Data.Remote.Mapper.Mapper
import com.example.weather.SearchScreen.Data.Remote.autoSearchApi.AutoCompleteApi
import com.example.weather.SearchScreen.Domain.Model.AutoComplete
import com.example.weather.SearchScreen.Domain.Model.AutoSearchError
import com.example.weather.SearchScreen.Domain.Repository.AutoSearchRepository

class AutoSearchRepositoryImpl(private val api : AutoCompleteApi): AutoSearchRepository {
    override suspend fun getSearchedAutoComplete(searchedLocation : String): AutoCompleteResult<List<AutoComplete> , AutoSearchError> {
        return try {
            val dtos = api.searchLocation(searchedLocation)
            val domainModels = dtos.map {
                Mapper.AutoCompleteDtoToDomain(it)
            }
            AutoCompleteResult.Success(domainModels)
        } catch (e: Exception) {
            AutoCompleteResult.Error(AutoSearchError.Unknown)
        }
    }
}