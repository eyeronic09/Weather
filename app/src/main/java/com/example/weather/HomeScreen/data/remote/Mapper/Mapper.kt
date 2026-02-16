package com.example.weather.HomeScreen.data.remote.Mapper

import android.util.Log
import com.example.weather.HomeScreen.data.remote.Dtos.CurrentWeatherDto
import com.example.weather.HomeScreen.data.remote.Dtos.HourlyWeatherDto
import com.example.weather.domain.model.HourlyItem
import com.example.weather.domain.model.Weather


object Mapper {
    fun CurrentWeatherDtoToDomain(dto: CurrentWeatherDto): Weather {
        val hourly = dto.forecast?.forecastday?.firstOrNull()
        Log.d("hourly" , hourly.toString())
        return Weather(
            cityName = dto.location?.name ?: "",
            region = dto.location?.region ?: "",
            country = dto.location?.country ?: "",
            temperatureC = dto.current?.tempC ?: 0F,
            conditionText = dto.current?.condition?.text ?: "",
            conditionIconUrl = dto.current?.condition?.icon ?: "",
            windKph = dto.current?.windKph ?: 0,
            humidity = dto.current?.humidity ?: 0,
             forcastday = hourly?.hour?.map { HourlyItemToDomain(dto = it) } ?: emptyList(),
        )
    }

    fun HourlyItemToDomain(dto: HourlyWeatherDto): HourlyItem {
        return HourlyItem(
            time = dto.time,
            tempC = dto.tempC.toFloat(),
            conditionText = dto.condition.text
        )
    }

}