package com.example.weather.HomeScreen.data.remote.Mapper

import com.example.weather.HomeScreen.data.remote.Dtos.CurrentWeatherDto
import com.example.weather.HomeScreen.data.remote.Dtos.Hour
import com.example.weather.domain.model.HourlyItem
import com.example.weather.domain.model.Weather


fun CurrentWeatherDto.toDomain(): Weather {
    return Weather(
        cityName = this.location?.name ?: "Unknown",
        region = this.location?.region ?: "",
        country = this.location?.country ?: "",
        temperatureC = this.current?.tempC?.toFloat() ?: 0f,
        conditionText = this.current?.condition?.text ?: "Unknown",
        conditionIconUrl = this.current?.condition?.icon ?: "",
        windKph = this.current?.windKph?.toFloat() ?: 0f,
        humidity = this.current?.humidity ?: 0,
        hourly = forecast
            ?.forecastday
            ?.firstOrNull()
            ?.hour
            ?.map { it.toDomain() }
            ?: emptyList()
    )
}


fun Hour.toDomain(): HourlyItem {
    return HourlyItem(
        time = extractTime(time),
        tempC = tempC.toFloat(),
        conditionText = condition?.text ?: "Unknown"
    )
}

private fun extractTime(dateTime: String): String {
    return dateTime.substringAfter(" ")
}
