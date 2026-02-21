package com.example.weather.HomeScreen.UI_Presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.HomeScreen.data.remote.Mapper.Result
import com.example.weather.HomeScreen.domain.repository.WeatherRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenVM(
    private val repository: WeatherRepository
) : ViewModel() {
    private val _Uistate = MutableStateFlow(WeatherState())
    val uiState : StateFlow<WeatherState> = _Uistate.asStateFlow()

    fun onEvent(onEvent: HomeScreenEvent){
        when(onEvent) {
            is HomeScreenEvent.SearchWeather -> TODO()
            is HomeScreenEvent.UpdateSearchCityInput -> {
                getDefaultWeather(onEvent.city)
            }
        }
    }


    private fun getDefaultWeather(cityInput: String){
        viewModelScope.launch {
            _Uistate.update { it.copy(isLoading = true, error = null) }


            
            val result = repository.getCurrentWeather(cityInput)
            Log.d(result.toString() , result.toString())
            
            _Uistate.update { state ->
                when (result) {
                    is Result.Success -> state.copy(
                        isLoading = false,
                        currentWeather = result.data,
                        hourlyWeather = result.data.forcastday,
                        error = null
                    )
                    is Result.Error -> state.copy(
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