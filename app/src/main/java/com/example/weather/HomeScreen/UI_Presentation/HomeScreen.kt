package com.example.weather.HomeScreen.UI_Presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
        val navigator = LocalNavigator.currentOrThrow
        val viewmodel = koinViewModel<HomeScreenVM>()

        WeatherScreenRoot(
            viewmodel = viewmodel,
            onScreenClick = { event ->  }
        )

    }
}


@Composable
fun WeatherScreenRoot(
    viewmodel: HomeScreenVM = koinViewModel(),
    onScreenClick: (onEvent: HomeScreenEvent.SearchWeather) -> Unit,


    ) {
    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()

    WeatherScreenRootContent(
        uiState = uiState,
    )
}

@Composable
fun WeatherScreenRootContent(
    uiState: HomeScreenState,

    ) {
    Column {
        uiState.currentWeather?.let { weather ->
            WeatherDetailsCard(
                weather = weather
            )
        }
        uiState.hourlyWeather?.let { hourly ->
            HourlyFourCastCard(
                hourly = hourly
            )
        }
    }
}