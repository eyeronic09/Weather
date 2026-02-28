package com.example.weather.SettingScreen.UI_Layer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.SearchScreen.Data.Remote.Mapper.AutoCompleteResult
import com.example.weather.SearchScreen.Domain.Model.AutoComplete
import com.example.weather.SearchScreen.Domain.Repository.AutoSearchRepository
import com.example.weather.SettingScreen.domain.repository.HomeLocationPrefRepository
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
    val HomeLocation: String = "",
    val ShowPOP: Boolean = false,
    // this is currentQuery for insertLocations Composable
    val currentQuery: String = "",
    val onQueryChange: String = "",
    val autoComplete: List<AutoComplete> = emptyList(),
    val error: String? = ""
)

sealed interface SettingLocationEvent {
    object showThePoPUp : SettingLocationEvent
    object hideThatPopUp : SettingLocationEvent
    data class SetLocation(val onLocationChange: String) : SettingLocationEvent
    data class QueryChange(val onQueryChange: String) : SettingLocationEvent
}

class SettingVM(
    private val repository: HomeLocationPrefRepository,
    private val autoSearchRepository: AutoSearchRepository
) : ViewModel() {
    private val _UiState = MutableStateFlow(SettingScreenUiState())
    val UiState: StateFlow<SettingScreenUiState> = _UiState.asStateFlow()

    init {

        observeQueryChanges()
    }

    fun onEvent(event: SettingLocationEvent) {
        when (event) {
            is SettingLocationEvent.SetLocation -> setSetLocation(event.onLocationChange)
            is SettingLocationEvent.showThePoPUp -> _UiState.update { it.copy(ShowPOP = true) }
            is SettingLocationEvent.hideThatPopUp -> _UiState.update { it.copy(ShowPOP = false) }
            is SettingLocationEvent.QueryChange -> _UiState.update {
                it.copy(currentQuery = event.onQueryChange)
            }
        }
    }

    private fun observeQueryChanges() {
        viewModelScope.launch {
            UiState
                .map { it.currentQuery }
                .distinctUntilChanged()
                .debounce(500L) // Reduced debounce for better UX
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
}