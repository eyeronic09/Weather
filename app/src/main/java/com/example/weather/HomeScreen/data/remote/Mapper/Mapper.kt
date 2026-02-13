package com.example.weather.HomeScreen.data.remote.Mapper

import com.example.weather.HomeScreen.data.remote.Dtos.WeatherResponseDto
import com.example.weather.HomeScreen.domain.model.Weather
import com.example.weather.HomeScreen.domain.model.HourlyWeather

fun WeatherResponseDto.toDomain (): Weather? {
    // Check if there's an error
    error?.let {
        return null
    }
    
    // Check if required data is available
    return if (location != null && currentWeather != null && forecast != null) {
        Weather(
            cityName = location.name,
            region = location.region,
            country = location.country,
            temperatureC = currentWeather.tempC,
            conditionText = currentWeather.condition.text,
            conditionIconUrl = currentWeather.condition.icon,
            windKph = currentWeather.windKph,
            humidity = currentWeather.humidity,
            hourlyForecast = forecast.forecastDay.firstOrNull()?.hour?.map { hourly ->
                HourlyWeather(
                    temperatureC = hourly.tempC,
                    time = hourly.time,
                    conditionIconUrl = hourly.condition.icon,
                    conditionText = hourly.condition.text
                )
            } ?: emptyList()
        )
    } else {
        null
    }
}