package com.example.weather.SearchScreen.Ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import com.example.weather.SearchScreen.Ui.Component.AutoSuggestion
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
    onAction :(SearchEvent) -> Unit
) {
    SearchContent(
        state = state,
        onAction = onAction
    )
}


@Composable
fun SearchContent(
    state : SearchScreenUiState,
    onAction: (SearchEvent) -> Unit
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }
            else ->{
                AutoSuggestion(
                    suggestion = state,
                    onValue = {onAction(SearchEvent.OnQueryChange(it))}
                )
            }

        }
    }}

