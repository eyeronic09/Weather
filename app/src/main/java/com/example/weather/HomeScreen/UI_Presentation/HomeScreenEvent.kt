package com.example.weather.HomeScreen.UI_Presentation


sealed interface HomeScreenEvent  {
    data class UpdateSearchCityInput(val city: String) : HomeScreenEvent
    data class SearchWeather(val city: String) : HomeScreenEvent
}