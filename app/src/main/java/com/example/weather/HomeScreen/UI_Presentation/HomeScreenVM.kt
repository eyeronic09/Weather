package com.example.weather.HomeScreen.UI_Presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.HomeScreen.domain.use_case.GetWeatherUseCases
import com.example.weather.SettingScreen.domain.repository.SettingPrefRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@OptIn(ExperimentalCoroutinesApi::class)
class HomeScreenVM(
    private val getWeatherUseCases: GetWeatherUseCases,
    private val settingPrefRepository: SettingPrefRepository
) : ViewModel() {
    private val _Uistate = MutableStateFlow(WeatherState())
    val uiState: StateFlow<WeatherState> = _Uistate.asStateFlow()

    private val _searchCityInput = MutableStateFlow("")

    init {
        viewModelScope.launch {
            _searchCityInput
                .flatMapLatest { city ->
                    getWeatherUseCases(city)
                }
                .collect { newState ->
                    _Uistate.update { currentState ->
                        newState.copy(isTempC = currentState.isTempC)
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
                    val currentCity = _searchCityInput.value
                    getWeatherUseCases(currentCity).collect { newState ->
                        _Uistate.update { currentState ->
                            newState.copy(isTempC = currentState.isTempC)
                        }
                    }
                }
            }
        }
    }
}