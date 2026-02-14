package com.example.weather.HomeScreen.data.remote.Mapper

import com.example.weather.HomeScreen.data.remote.Dtos.WeatherResponseDto
import com.example.weather.HomeScreen.domain.model.Weather

fun WeatherResponseDto.toDomain (): Weather {
    return Weather(
        cityName = this.location?.name ?: "Unknown",
        region = this.location?.region ?: "",
        country = this.location?.country ?: "",
        temperatureC = this.current?.tempC?.toFloat() ?: 0f,
        conditionText = this.current?.condition?.text ?: "Unknown",
        conditionIconUrl = this.current?.condition?.icon ?: "",
        windKph = this.current?.windKph?.toFloat() ?: 0f,
        humidity = this.current?.humidity ?: 0L
    )
}