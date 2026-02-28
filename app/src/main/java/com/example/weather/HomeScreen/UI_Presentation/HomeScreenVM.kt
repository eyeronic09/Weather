package com.example.weather.HomeScreen.UI_Presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.HomeScreen.data.remote.Mapper.Result
import com.example.weather.HomeScreen.domain.repository.WeatherRepository
import com.example.weather.SettingScreen.domain.repository.HomeLocationPrefRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class HomeScreenVM(
    private val repository: WeatherRepository,
    private val homeLocationPrefRepository: HomeLocationPrefRepository
) : ViewModel() {
    private val _Uistate = MutableStateFlow(WeatherState())
    val uiState : StateFlow<WeatherState> = _Uistate.asStateFlow()

    private val _searchCityInput = MutableStateFlow<String?>(null)
    
    init {
        // Combine home location and manual search input
        viewModelScope.launch {
            combine(
                homeLocationPrefRepository.readDefaultLocation(),
                _searchCityInput
            ) { homeLocation, searchInput ->
                // Use search input if provided, otherwise use home location
                searchInput ?: homeLocation
            }
            .filterNotNull() // Only proceed if we have a location
            .flatMapLatest { location ->
                // Convert location to weather data
                fetchWeatherFlow(location)
            }
            .collect { weatherState ->
                _Uistate.value = weatherState
            }
        }
    }

    fun onEvent(onEvent: HomeScreenEvent){
        when(onEvent) {
            is HomeScreenEvent.UpdateSearchCityInput -> {
                _searchCityInput.value = onEvent.city.ifBlank { null }
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
                        hourlyWeather = result.data.forcastday,
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

