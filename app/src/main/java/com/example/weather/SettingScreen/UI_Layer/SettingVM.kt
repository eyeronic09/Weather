package com.example.weather.SettingScreen.UI_Layer

import android.content.Context
import androidx.compose.ui.input.pointer.HistoricalChange
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.SearchScreen.Domain.Model.AutoComplete
import com.example.weather.SearchScreen.Domain.Repository.AutoSearchRepository
import com.example.weather.SettingScreen.domain.repository.HomeLocationPrefRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SettingScreenUiState(
    val HomeLocation: String = "",
    val ShowPOP : Boolean = false,
    // this is currentQuery for insertLocations Composable
    val currentQuery : String = "",
    val onQueryChange: String = "",
    val autoComplete : List<AutoComplete> = emptyList()
)
sealed interface SettingLocationEvent {
    object showThePoPUp : SettingLocationEvent
    object hideThatPopUp : SettingLocationEvent
    data class SetLocation(val onLocationChange : String, val context: Context) : SettingLocationEvent

    data class QueryChange(val onQueryChange: String) : SettingLocationEvent

}
class SettingVM(
    private val repository: HomeLocationPrefRepository,
    private val autoSearchRepository: AutoSearchRepository
) : ViewModel() {
    private val _UiState = MutableStateFlow(SettingScreenUiState())
    val UiState: StateFlow<SettingScreenUiState> = _UiState.asStateFlow()

    fun onEvent(event: SettingLocationEvent) {
        when(event){
            is SettingLocationEvent.SetLocation -> getSetLocation(event.context)
            is SettingLocationEvent.showThePoPUp -> _UiState.update { it.copy(ShowPOP = true) }
            is SettingLocationEvent.hideThatPopUp -> _UiState.update { it.copy(ShowPOP = false) }
            is SettingLocationEvent.QueryChange -> _UiState.update {
                it.copy(currentQuery = event.onQueryChange)
            }
        }
    }

    private fun getSetLocation(context: Context) {
        viewModelScope.launch {
            repository.readDeafaultLocation(context).collectLatest { location ->
                _UiState.update {
                    it.copy(HomeLocation = location ?: "")
                }
            }
        }
    }


}