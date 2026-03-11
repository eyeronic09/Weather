package com.example.weather.HomeScreen.data.remote.Mapper

import androidx.compose.ui.semantics.text
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
            isDay = dto.current?.isDay == 1,
            conditionText = dto.current?.condition?.text ?: "",
            conditionIconUrl = dto.current?.condition?.icon ?: "",
            windMph = dto.current?.windMph ?: 0.0,
            windKph = dto.current?.windKph ?: 0.0,
            windDegree = dto.current?.windDegree ?: 0,
            windDir = dto.current?.windDir ?: "",
            pressureMb = dto.current?.pressureMb ?: 0.0,
            pressureIn = dto.current?.pressureIn ?: 0.0,
            precipMm = dto.current?.precipMm ?: 0.0,
            precipIn = dto.current?.precipIn ?: 0.0,
            humidity = dto.current?.humidity ?: 0,
            cloud = dto.current?.cloud ?: 0,
            feelsLikeC = dto.current?.feelsLikeC ?: 0.0,
            feelsLikeF = dto.current?.feelsLikeF ?: 0.0,
            windChillC = dto.current?.windChillC ?: 0.0,
            windChillF = dto.current?.windChillF ?: 0.0,
            heatIndexC = dto.current?.heatIndexC ?: 0.0,
            heatIndexF = dto.current?.heatIndexF ?: 0.0,
            dewPointC = dto.current?.dewPointC ?: 0.0,
            dewPointF = dto.current?.dewPointF ?: 0.0,
            visKm = dto.current?.visKm ?: 0.0,
            visMiles = dto.current?.visMiles ?: 0.0,
            uv = dto.current?.uv ?: 0.0,
            gustMph = dto.current?.gustMph ?: 0.0,
            gustKph = dto.current?.gustKph ?: 0.0,
            airQuality = dto.current?.airQuality?.usEpaIndex ?: 0,
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