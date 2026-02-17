package com.example.weather.HomeScreen.UI_Presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.weather.HomeScreen.UI_Presentation.component.HourlyFourCastCard
import com.example.weather.HomeScreen.UI_Presentation.component.WeatherDetailsCard
import org.koin.androidx.compose.koinViewModel // Critical import

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        WeatherRoute()
    }
}


@Composable
fun WeatherRoute(
    viewModel: HomeScreenVM = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    WeatherScreen(
        state = state,
        onAction = viewModel::onEvent
    )
}

@Composable
fun WeatherScreen(
    state: HomeScreenState,
    onAction: (HomeScreenEvent) -> Unit
) {
    Scaffold(
        topBar = {  },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAction(HomeScreenEvent.SearchWeather("")) }
            ) { Text("Go") }
        }
    ) { padding ->

        WeatherContent(
            state = state,
            modifier = Modifier.padding(padding),
            onAction = onAction
        )
    }
}

@Composable
fun WeatherContent(
    state: HomeScreenState,
    modifier: Modifier,
    onAction: (HomeScreenEvent) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }
            state.error != null -> {
                Text(
                    text = state.error,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error
                )
            }
            state.currentWeather != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    WeatherDetailsCard(weather = state.currentWeather)
                    HourlyFourCastCard(
                        hourly = state.hourlyWeather!!
                    )
                }
            }
            else -> {
                Text(
                    text = "Search for a city to see weather",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

