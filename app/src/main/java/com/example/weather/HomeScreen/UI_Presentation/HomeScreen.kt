package com.example.weather.HomeScreen.UI_Presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WeatherScreenRoot(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun WeatherScreenRoot(
    uiState: HomeScreenState,
    onEvent: (HomeScreenEvent) -> Unit
) {
    Scaffold { padding ->
        WeatherScreenRootContent(
            uiState = uiState,
            modifier = Modifier.padding(padding),
            onEvent = onEvent
        )
    }
}

@Composable
fun WeatherScreenRootContent(
    uiState: HomeScreenState,
    modifier: Modifier = Modifier,
    onEvent: (HomeScreenEvent) -> Unit
) {
    Column(modifier = modifier) {
        when {
            uiState.isLoading -> TODO()
            uiState.error != null -> TODO()
            uiState.currentWeather != null -> {
                WeatherDetailsCard(weather = uiState.currentWeather)

                uiState.hourlyWeather?.let {
                    HourlyFourCastCard(hourly = it)
                }
            }
            else -> TODO()
        }
    }
}

