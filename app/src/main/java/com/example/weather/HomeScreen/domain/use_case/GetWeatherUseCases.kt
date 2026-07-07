package com.example.weather.HomeScreen.domain.use_case

import com.example.weather.HomeScreen.UI_Presentation.WeatherState
import com.example.weather.HomeScreen.data.remote.Mapper.Result
import com.example.weather.HomeScreen.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWeatherUseCases(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(searchCity: String): Flow<WeatherState> = flow {
        emit(value = WeatherState(isLoading = true, searchWeatherCity = searchCity))
        try {
            when (val result = weatherRepository.getCurrentWeather(searchCity)) {
                is Result.Success -> {
                    emit(
                        value = WeatherState(
                            isLoading = false,
                            currentWeather = result.data,
                            hourlyWeather = result.data.forecastDays.flatMap { it.hourlyForecasts },
                            searchWeatherCity = searchCity
                        )
                    )
                }
                is Result.Error -> {
                    emit(
                        value = WeatherState(
                            isLoading = false,
                            error = result.errorMessage.toString(),
                            searchWeatherCity = searchCity
                        )
                    )
                }
            }
        } catch (e: Exception) {
            emit(
                WeatherState(
                    isLoading = false,
                    error = e.localizedMessage ?: "Unknown Error",
                    searchWeatherCity = searchCity
                )
            )
        }
    }
}