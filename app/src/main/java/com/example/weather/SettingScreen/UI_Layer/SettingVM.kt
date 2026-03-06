package com.example.weather.SettingScreen.UI_Layer

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
    val tempUnitOptions: List<String> = listOf("Celsius (°C)", "Fahrenheit (°F)"),
    val selectedTempUnit: String = "Celsius (°C)",
    val homeLocation: String = "Not Set",
    val isLocationDialogVisible: Boolean = false,
    val searchQuery: String = "",
    val searchResults: List<AutoComplete> = emptyList(),
    val isSearching: Boolean = false,
    val error: String? = null
)

sealed interface SettingEvent {
    object ShowLocationDialog : SettingEvent
    object DismissLocationDialog : SettingEvent
    data class UpdateSearchQuery(val query: String) : SettingEvent
    data class SaveLocation(val city: String) : SettingEvent
    data class ChangeTempUnit(val unit: String) : SettingEvent
}

class SettingVM(
    private val settingPrefRepository: SettingPrefRepository,
    private val autoSearchRepository: AutoSearchRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingScreenUiState())
    val uiState: StateFlow<SettingScreenUiState> = _uiState.asStateFlow()

    init {
        loadSettings()
        observeSearchQuery()
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

    private fun observeSearchQuery() {
        viewModelScope.launch {
            _uiState
                .map { it.searchQuery }
                .distinctUntilChanged()
                .debounce(500L)
                .collectLatest { query ->
                    if (query.length >= 2) {
                        performSearch(query)
                    } else {
                        _uiState.update { it.copy(searchResults = emptyList(), isSearching = false) }
                    }
                }
        }
    }

    private suspend fun performSearch(query: String) {
        _uiState.update { it.copy(isSearching = true, error = null) }
        when (val result = autoSearchRepository.getSearchedAutoComplete(query)) {
            is AutoCompleteResult.Success -> {
                _uiState.update { it.copy(searchResults = result.data, isSearching = false) }
            }
            is AutoCompleteResult.Error -> {
                _uiState.update { it.copy(error = result.errorMessage.toString(), isSearching = false) }
            }
        }
    }

    fun onEvent(event: SettingEvent) {
        when (event) {
            SettingEvent.ShowLocationDialog -> {
                _uiState.update { it.copy(isLocationDialogVisible = true, searchQuery = "", searchResults = emptyList()) }
            }
            SettingEvent.DismissLocationDialog -> {
                _uiState.update { it.copy(isLocationDialogVisible = false) }
            }
            is SettingEvent.UpdateSearchQuery -> {
                _uiState.update { it.copy(searchQuery = event.query) }
            }
            is SettingEvent.SaveLocation -> {
                viewModelScope.launch {
                    settingPrefRepository.saveDefaultLocation(event.city)
                    _uiState.update { it.copy(isLocationDialogVisible = false) }
                }
            }
            is SettingEvent.ChangeTempUnit -> {
                val unitValue = if (event.unit.contains("Fahrenheit")) "F" else "C"
                viewModelScope.launch {
                    settingPrefRepository.saveDefaultTempUnit(unitValue)
                }
            }
        }
    }
}
