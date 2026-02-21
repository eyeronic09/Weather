package com.example.weather.SearchScreen.Domain.Repository

import com.example.weather.SearchScreen.Data.Remote.Mapper.AutoCompleteResult
import com.example.weather.SearchScreen.Domain.Model.AutoComplete
import com.example.weather.SearchScreen.Domain.Model.AutoSearchError

interface AutoSearchRepository {
    suspend fun getSearchedAutoComplete(searchedLocation : String) : AutoCompleteResult<List<AutoComplete> , AutoSearchError>
}