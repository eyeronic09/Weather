package com.example.weather.SearchScreen

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.SearchScreen.Domain.SearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


sealed interface SearchEvent {
    data class onSearhClick(val query : String) : SearchEvent

}

class SearchScreenVM(
    private val repository: SearchRepository
) : ViewModel() {
    private val _cityQuery = MutableStateFlow("")
    val cityQuery: StateFlow<String> = _cityQuery.asStateFlow()

    private val _predictions = MutableStateFlow<List<String>>(emptyList())
    val predictions: StateFlow<List<String>> = _predictions.asStateFlow()

    fun onQueryChange(newQuery: String) {
        _cityQuery.value = newQuery
    }

    init {
        viewModelScope.launch {
            _cityQuery.debounce(3000).distinctUntilChanged().collectLatest { text ->
                if (text.isNotBlank()) {
                    _predictions.value = repository.getPredictions(text)
                } else {
                    _predictions.value = emptyList()
                }
            }

        }

    }

    fun onEvent(onevent : SearchEvent){
        when(onevent){
            is SearchEvent.onSearhClick -> onNavigation()
        }
    }

    fun onNavigation() {

    }


}
