package com.example.weather.SettingScreen.UI_Layer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.SearchScreen.Data.Remote.Mapper.AutoCompleteResult
import com.example.weather.SearchScreen.Domain.Model.AutoComplete
import com.example.weather.SearchScreen.Domain.Repository.AutoSearchRepository
import com.example.weather.SettingScreen.domain.repository.SettingPrefRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SettingScreenUiState(
    val options: List<String> = listOf("Celsius (°C)", "Fahrenheit (°F)"),
    val selectedTempUnit: String = "Celsius (°C)",
    val homeLocation: String = "",
    val showPopUp: Boolean = false,
    val currentQuery: String = "",
    val autoComplete: List<AutoComplete> = emptyList(),
    val isSearching: Boolean = false,
    val error: String? = null
)

sealed interface SettingEvent {
    object ShowAddLocationPopUp : SettingEvent
    object DismissPopUp : SettingEvent
    data class SelectLocation(val city: String) : SettingEvent
    data class QueryChange(val query: String) : SettingEvent
    data class SetTempUnit(val unit: String) : SettingEvent
}

class SettingVM(
    private val settingPrefRepository: SettingPrefRepository,
    private val autoSearchRepository: AutoSearchRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingScreenUiState())
    val uiState: StateFlow<SettingScreenUiState> = _uiState.asStateFlow()

    init {
        observeQueryChanges()
        loadSettings()
    }

    private fun loadSettings() {
        viewModelScope.launch {
            launch {
                settingPrefRepository.readDefaultLocation().collect { location ->
                    _uiState.update { it.copy(homeLocation = location ?: "Not Set") }
                }
            }
            launch {
                settingPrefRepository.readDefaultTempUnit().collect { unit ->
                    val displayUnit = if (unit == "F") "Fahrenheit (°F)" else "Celsius (°C)"
                    _uiState.update { it.copy(selectedTempUnit = displayUnit) }
                }
            }
        }
    }

    fun onEvent(event: SettingEvent) {
        when (event) {
            is SettingEvent.SelectLocation -> {
                saveLocation(event.city)
            }
            SettingEvent.ShowAddLocationPopUp -> {
                _uiState.update { it.copy(showPopUp = true, currentQuery = "", autoComplete = emptyList()) }
            }
            SettingEvent.DismissPopUp -> {
                _uiState.update { it.copy(showPopUp = false) }
            }
            is SettingEvent.QueryChange -> {
                _uiState.update { it.copy(currentQuery = event.query) }
            }
            is SettingEvent.SetTempUnit -> {
                saveTempUnit(event.unit)
            }
        }
    }

    private fun observeQueryChanges() {
        viewModelScope.launch {
            _uiState
                .map { it.currentQuery }
                .distinctUntilChanged()
                .debounce(500L)
                .collectLatest { query ->
                    if (query.length >= 2) {
                        searchLocation(query)
                    } else {
                        _uiState.update { it.copy(autoComplete = emptyList(), isSearching = false) }
                    }
                }
        }
    }

    private suspend fun searchLocation(query: String) {
        _uiState.update { it.copy(isSearching = true, error = null) }
        when (val result = autoSearchRepository.getSearchedAutoComplete(query)) {
            is AutoCompleteResult.Success -> {
                _uiState.update { it.copy(autoComplete = result.data, isSearching = false) }
            }
            is AutoCompleteResult.Error -> {
                _uiState.update { it.copy(error = result.errorMessage.toString(), isSearching = false) }
            }
        }
    }

    private fun saveLocation(location: String) {
        viewModelScope.launch {
            settingPrefRepository.saveDefaultLocation(location)
            _uiState.update { it.copy(showPopUp = false) }
        }
    }

    private fun saveTempUnit(displayUnit: String) {
        val unitValue = if (displayUnit.contains("Fahrenheit")) "F" else "C"
        viewModelScope.launch {
            settingPrefRepository.saveDefaultTempUnit(unitValue)
        }
    }
}
