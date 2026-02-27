package com.example.weather.SettingScreen.UI_Layer

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.SearchScreen.Domain.Repository.AutoSearchRepository
import com.example.weather.SettingScreen.domain.repository.HomeLocationPrefRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

data class SetLocation(
    val HomeLocation: StateFlow<String?> = MutableStateFlow(""),
    val ShowPOP : Boolean = false,

)
sealed interface SettingLocationEvent {
    object showThePoPUp : SettingLocationEvent
    data class SetLocation(val onLocationChange : String, val context: Context) : SettingLocationEvent

}
class SettingVM(
    private val repository: HomeLocationPrefRepository,
    private val autoSearchRepository: AutoSearchRepository
) : ViewModel() {
    private val _UiState = MutableStateFlow(SetLocation())
    val UiState: StateFlow<SetLocation> = _UiState.asStateFlow()

    fun onEvent(event: SettingLocationEvent) {
        when(event){
            is SettingLocationEvent.SetLocation -> getSetLocation(event.context)
            is SettingLocationEvent.showThePoPUp -> TODO()
        }
    }

    private fun getSetLocation(context: Context ) {
        _UiState.update {
            it.copy(
                HomeLocation = repository.readDeafaultLocation(
                    context = context
                ).stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = ""
                )
            )            
        }
    }
}