package com.example.weather.HomeScreen.data.remote.Mapper

import com.example.weather.HomeScreen.data.remote.Dtos.WeatherResponseDto
import com.example.weather.HomeScreen.domain.model.Weather

fun WeatherResponseDto.toDomain () : Weather {
    return Weather(
        cityName = this.location.name,
        region = this.location.region,
        country = this.location.country,
        temperatureC = this.currentWeather.tempC,
        conditionText = this.currentWeather.condition.text,
        conditionIconUrl = this.currentWeather.condition.icon,
        windKph = this.currentWeather.windKph,
        humidity = this.currentWeather.humidity
    )
}