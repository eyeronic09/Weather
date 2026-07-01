package com.example.weather.HomeScreen.data.remote.Mapper

import com.example.weather.HomeScreen.data.remote.Dtos.CurrentWeatherDto
import com.example.weather.core.util.WeatherAnimation
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
            animation = (dto.current?.condition?.code?.toInt() ?: 0).toWeatherAnimation(),
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
                            conditionText = hourlyDto.condition.text,
                            animation = hourlyDto.condition.code.toInt().toWeatherAnimation(),
                            code = hourlyDto.condition.code
                        )
                    }
                )
            } ?: emptyList(),
            code = dto.current?.condition?.code ?: 0
        )
    }
}

fun Int.toWeatherAnimation(): WeatherAnimation =
    when (this) {
        1000 -> WeatherAnimation.Sunny

        1003 -> WeatherAnimation.PartlyCloudy

        1006, 1009 ->
            WeatherAnimation.Cloudy

        1030, 1135, 1147 ->
            WeatherAnimation.Fog

        1063, 1150, 1153,
        1180, 1183, 1240 ->
            WeatherAnimation.LightRain

        1186, 1189, 1192,
        1195, 1243, 1246 ->
            WeatherAnimation.HeavyRain

        1087, 1273, 1276,
        1279, 1282 ->
            WeatherAnimation.Thunderstorm

        1066, 1114, 1117,
        in 1210..1237,
        1255, 1258 ->
            WeatherAnimation.Snow

        else ->
            WeatherAnimation.Sunny
    }