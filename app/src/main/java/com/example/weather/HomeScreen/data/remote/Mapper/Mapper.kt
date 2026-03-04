package com.example.weather.HomeScreen.data.remote.Mapper

import com.example.weather.HomeScreen.data.remote.Dtos.CurrentWeatherDto
import com.example.weather.domain.model.ForecastDay
import com.example.weather.domain.model.HourlyForecast
import com.example.weather.domain.model.Weather

object Mapper {
    fun CurrentWeatherDtoToDomain(dto: CurrentWeatherDto): Weather {
        return Weather(
            cityName = dto.location?.name ?: "",
            region = dto.location?.region ?: "",
            country = dto.location?.country ?: "",
            temperatureC = dto.current?.tempC ?: 0.0,
            temperatureF = dto.current?.tempF ?: 0.0,
            conditionText = dto.current?.condition?.text ?: "",
            conditionIconUrl = dto.current?.condition?.icon ?: "",
            windKph = dto.current?.windKph ?: 0.0,
            humidity = dto.current?.humidity ?: 0,
            forecastDays = dto.forecast?.forecastday?.map { forecastDayDto ->
                ForecastDay(
                    date = forecastDayDto.date,
                    hourlyForecasts = forecastDayDto.hour.map { hourlyDto ->
                        HourlyForecast(
                            time = hourlyDto.time,
                            tempC = hourlyDto.tempC.toFloat(),
                            tempF = hourlyDto.tempF.toFloat(),
                            icon = hourlyDto.condition.icon,
                            conditionText = hourlyDto.condition.text
                        )
                    }
                )
            } ?: emptyList()
        )
    }
}
