package com.example.weather.SearchScreen.Ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.weather.SearchScreen.Ui.Component.AutoSuggestion
import com.example.weather.HomeScreen.UI_Presentation.HomeScreen
import org.koin.androidx.compose.koinViewModel

class Search_Screen : Screen {
    @Composable
    override fun Content() {
        SearchRoute()
    }
}

@Composable
fun SearchRoute(
    viewModel : SearchScreenVM = koinViewModel()
){
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreen(
        state = state,
        onAction = viewModel::onEvent
    )
}

@Composable
fun SearchScreen(
    state: SearchScreenUiState,
    onAction: (SearchEvent) -> Unit
) {

    Scaffold() {
        SearchContent(
            state = state,
            onAction = onAction,
            modifier = Modifier.padding(it)
        )
    }


}


@Composable
fun SearchContent(
    state : SearchScreenUiState,
    onAction: (SearchEvent) -> Unit,
    modifier: Modifier
){
    val navigator = LocalNavigator.current
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }
            state.error != null ->{
                Text("Error not found")
            }
            else ->{
                AutoSuggestion(
                    suggestion = state,
                    onValue = {onAction(SearchEvent.OnQueryChange(it))},
                    onItemClicked = { suggestion ->
                        onAction(SearchEvent.OnSuggestionClicked(suggestion))
                        val locationName = "${suggestion.name}, ${suggestion.region}, ${suggestion.country}"
                        navigator?.push(HomeScreen(locationName))
                    }
                )
            }

        }
    }}

