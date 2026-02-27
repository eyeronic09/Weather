package com.example.weather.SearchScreen.Ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.SearchScreen.Data.Remote.Mapper.AutoCompleteResult
import com.example.weather.SearchScreen.Domain.Model.AutoComplete
import com.example.weather.SearchScreen.Domain.Repository.AutoSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


sealed interface SearchEvent {
    data class OnQueryChange(val newQuery: String) : SearchEvent
    object OnSearchClick : SearchEvent
    data class OnSuggestionClicked(val suggestion: AutoComplete) : SearchEvent
}
data class SearchScreenUiState(
    val autoSuggestion : List<AutoComplete?> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery : String = "",
    val error: String? = null
)
class SearchScreenVM(
    private val autoSearchRepository: AutoSearchRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchScreenUiState())
    val uiState: StateFlow<SearchScreenUiState> = _uiState.asStateFlow()



    init {
        observeQueryChanges()
    }

    fun onEvent(event: SearchEvent) {
        when (event) {

            is SearchEvent.OnQueryChange -> {
                _uiState.update { it.copy(searchQuery = event.newQuery) }
            }
            is SearchEvent.OnSearchClick -> {
                val query = _uiState.value.searchQuery
                if (query.isNotBlank()) {
                    getSearch(query)
                }
            }
            is SearchEvent.OnSuggestionClicked -> {
                // Handle suggestion click - you can navigate or update state here
                _uiState.update { 
                    it.copy(
                        searchQuery = "${event.suggestion.name}, ${event.suggestion.region}, ${event.suggestion.country}"
                    )
                }
            }

        }
    }

    private fun observeQueryChanges() {
        viewModelScope.launch {
            uiState
                .map { it -> it.searchQuery }
                .distinctUntilChanged()
                .debounce(2000L)
                .collectLatest { query ->
                    if (query.isNotBlank()) {
                        getSearch(query)
                    } else {
                        _uiState.update { it.copy(autoSuggestion = emptyList()) }
                    }
                }
        }
    }


     fun getSearch(location: String) {

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            when (val result = autoSearchRepository.getSearchedAutoComplete(location)) {
                is AutoCompleteResult.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.errorMessage.toString()
                        )
                    }
                }

                is AutoCompleteResult.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            autoSuggestion = result.data
                        )
                    }
                }
            }
        }
    }
}
