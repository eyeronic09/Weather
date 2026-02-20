package com.example.weather.SearchScreen.Domain

class SearchRepository {

    private val cities = listOf(
        "London", "Los Angeles", "Lahore",
        "Long Beach", "Lisbon", "Londonderry"
    )

    suspend fun getPredictions(query: String): List<String> {
        return cities.filter {
            it.startsWith(query, ignoreCase = true)
        }
    }
}