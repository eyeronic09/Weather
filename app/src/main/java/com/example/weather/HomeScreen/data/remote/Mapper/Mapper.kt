package com.example.weather.HomeScreen.data.remote.Mapper

import com.example.weather.HomeScreen.data.remote.Dtos.WeatherResponse
import com.example.weather.HomeScreen.domain.model.Weather

fun WeatherResponse.toDomain () : Weather {
    return Weather(
        cityName = this.location.name,
        region = this.location.region,
        country = this.location.country,
        temperatureC = this.current.temp_c,
        conditionText = this.current.condition.text,
        conditionIconUrl = this.current.condition.icon,
        windKph = this.current.wind_kph,
        humidity = this.current.humidity
    )
}