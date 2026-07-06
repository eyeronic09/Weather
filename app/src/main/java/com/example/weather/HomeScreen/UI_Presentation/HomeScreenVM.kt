package com.example.weather.HomeScreen.UI_Presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.HomeScreen.data.remote.Mapper.Result
import com.example.weather.HomeScreen.domain.repository.WeatherRepository
import com.example.weather.SettingScreen.domain.repository.SettingPrefRepository
import com.example.weather.core.util.getWeatherGifRes
import com.example.weather.domain.model.Weather
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.KoinApplication.Companion.init


@OptIn(ExperimentalCoroutinesApi::class)
class HomeScreenVM(
    private val repository: WeatherRepository,
    private val settingPrefRepository: SettingPrefRepository
) : ViewModel() {
    private val _Uistate = MutableStateFlow(WeatherState())
    val uiState: StateFlow<WeatherState> = _Uistate.asStateFlow()

    private val _searchCityInput = MutableStateFlow("")

    init {
        viewModelScope.launch {
            _searchCityInput
                .flatMapLatest { city ->
                    fetchWeatherFlow(city)
                }
                .collect { newState ->
                    _Uistate.update { currentState ->
                        newState.copy(isTempC = currentState.isTempC);
                    }
                }
        }

        viewModelScope.launch {
            settingPrefRepository.readDefaultTempUnit().collect { unit ->
                unit?.let {
                    if (it == "C") {
                        _Uistate.update {
                            it.copy(
                                isTempC = true,
                            )
                        }
                    } else {
                        _Uistate.update {
                            it.copy(isTempC = false)
                        }
                    }
                }
            }

        }


    }

    fun onEvent(onEvent: HomeScreenEvent) {
        when (onEvent) {
            is HomeScreenEvent.UpdateSearchCityInput -> {
                _searchCityInput.value = onEvent.city
            }

            is HomeScreenEvent.onRefresh -> {
                viewModelScope.launch {
                    val currentState = _Uistate.value
                    if (currentState.searchWeatherCity.trim().isBlank()) return@launch

                    when (val result: Result<Weather, *> =
                        repository.getCurrentWeather(currentState.searchWeatherCity)) {
                        is Result.Error -> {
                            _Uistate.update {
                                it.copy(
                                    isLoading = false,
                                    error = result.errorMessage.toString()
                                )
                            }
                        }

                        is Result.Success -> {
                            if (currentState.currentWeather != result.data) {
                                _Uistate.update {
                                    it.copy(
                                        isLoading = false,
                                        currentWeather = result.data,
                                        hourlyWeather = result.data.forecastDays.flatMap { it.hourlyForecasts },
                                        error = null
                                    )
                                }
                            } else {
                                _Uistate.update { it.copy(isLoading = false) }
                            }
                        }

                    }
                }
            }
        }

    }


    private fun fetchWeatherFlow(cityInput: String): StateFlow<WeatherState> {
        return MutableStateFlow(WeatherState()).apply {
            viewModelScope.launch {
                value = value.copy(isLoading = true, error = null)

                val result = repository.getCurrentWeather(cityInput)
                Log.d("WeatherResult", result.toString())

                value = when (result) {
                    is Result.Success -> value.copy(
                        isLoading = false,
                        currentWeather = result.data,
                        hourlyWeather = result.data.forecastDays.flatMap { it.hourlyForecasts },
                        error = null
                    )
                    is Result.Error -> value.copy(
                        isLoading = false,
                        currentWeather = null,
                        hourlyWeather = null,
                        error = result.errorMessage.toString()
                    )
                }
            }
        }
    }
}
