package com.example.weather.SearchScreen.Data.Remote.Mapper

import com.example.weather.SearchScreen.Data.Remote.Dto.AutoCompleteDto
import com.example.weather.SearchScreen.Domain.Model.AutoComplete

object Mapper {
    fun AutoCompleteDtoToDomain(dto : AutoCompleteDto): AutoComplete {
        return AutoComplete(
            name = dto.name,
            region = dto.region,
            country = dto.country
        )
    }
}