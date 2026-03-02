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
    val options: List<String> = listOf("F", "C",),
    val selectedTempUnit : String = "F",
    val HomeLocation: String = "",
    val ShowPOP: Boolean = false,
    // this is currentQuery for insertLocations Composable
    val currentQuery: String = "",
    val onQueryChange: String = "",
    val autoComplete: List<AutoComplete> = emptyList(),
    val error: String = ""
)

sealed interface SettingLocationEvent {
    object ShowThatAddPopUp : SettingLocationEvent
    object HideThatPopUp : SettingLocationEvent
    data class SetLocation(val onLocationChange: String) : SettingLocationEvent
    data class QueryChange(val onQueryChange: String) : SettingLocationEvent
    data class SetTempUnit(val TempUnit: String) : SettingLocationEvent
}

class SettingVM(
    private val repository: SettingPrefRepository,
    private val autoSearchRepository: AutoSearchRepository,
    private val settingPrefRepository: SettingPrefRepository
) : ViewModel() {
    private val _UiState = MutableStateFlow(SettingScreenUiState())
    val UiState: StateFlow<SettingScreenUiState> = _UiState.asStateFlow()

    init {
        observeQueryChanges()
        getSelectedTempUnit()

    }

    fun onEvent(event: SettingLocationEvent) {
        when (event) {
            is SettingLocationEvent.SetLocation -> setSetLocation(event.onLocationChange)
            is SettingLocationEvent.ShowThatAddPopUp -> _UiState.update { it.copy(ShowPOP = true) }
            is SettingLocationEvent.HideThatPopUp -> _UiState.update { it.copy(ShowPOP = false) }
            is SettingLocationEvent.QueryChange -> _UiState.update {
                it.copy(currentQuery = event.onQueryChange)
            }

            is SettingLocationEvent.SetTempUnit -> setSelectedTempUnit(event.TempUnit)
        }
    }

    private fun observeQueryChanges() {
        viewModelScope.launch {
            UiState
                .map { it.currentQuery }
                .distinctUntilChanged()
                .debounce(1000L) // Reduced debounce for better UX
                .collectLatest { query ->
                    if (query.isNotBlank()) {
                        getSearch(query)
                    } else {
                        _UiState.update { it.copy(autoComplete = emptyList()) }
                    }
                }
        }
    }

    fun getSearch(location: String) {
        viewModelScope.launch {
            when (val result = autoSearchRepository.getSearchedAutoComplete(location)) {
                is AutoCompleteResult.Error -> {
                    _UiState.update {
                        it.copy(error = result.errorMessage.toString())
                    }
                }

                is AutoCompleteResult.Success -> {
                    _UiState.update {
                        it.copy(autoComplete = result.data)
                    }
                }
            }
        }
    }

    private fun setSetLocation(location: String) {
        viewModelScope.launch {
            repository.saveDefaultLocation(location)
            // Optionally hide popup after saving
            _UiState.update { it.copy(ShowPOP = false) }
        }
    }

    fun getSetLocation() {
        viewModelScope.launch {
            repository.readDefaultLocation().collect { location ->
                _UiState.update {
                    it.copy(HomeLocation = location ?: "Not Set")
                }
            }

        }
    }

     fun getSelectedTempUnit() {
         viewModelScope.launch {
             settingPrefRepository.readDefaultTempUnit().collect { unit ->
                 _UiState.update {
                     it.copy(selectedTempUnit = if (unit == "C") "C" else "F")
                 }
             }
         }
     }
    fun setSelectedTempUnit(string: String) {
        Log.d("SelectedTemp", string)
        viewModelScope.launch {
            settingPrefRepository.saveDefaultTempUnit(string)
            _UiState.update { it.copy(selectedTempUnit = string) }
        }
    }


}