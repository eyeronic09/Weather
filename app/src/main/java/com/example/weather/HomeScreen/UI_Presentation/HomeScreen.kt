package com.example.weather.HomeScreen.UI_Presentation

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.weather.HomeScreen.UI_Presentation.component.OverallStatices
import com.example.weather.HomeScreen.UI_Presentation.component.TopAppBarHeader
import com.example.weather.HomeScreen.UI_Presentation.component.WeatherDetailsCard
import com.example.weather.SearchScreen.Ui.Search_Screen
import com.example.weather.domain.model.Weather
import com.example.weather.domain.model.HourlyItem
import org.koin.androidx.compose.koinViewModel

class HomeScreen(val locationName : String): Screen {
    @Composable
    override fun Content() {
        WeatherRoute(locationName)

    }
}


@Composable
fun WeatherRoute(
    locationName: String,
    viewModel: HomeScreenVM = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(locationName) {
        viewModel.onEvent(HomeScreenEvent.UpdateSearchCityInput(locationName))
    }

    WeatherScreen(
        state = state,
        onAction = viewModel::onEvent
    )
}

@Composable
fun WeatherScreen(
    state: WeatherState,
    onAction: (HomeScreenEvent) -> Unit
) {
    val navigator = LocalNavigator.current
    Scaffold(
        topBar = {
            TopAppBarHeader(
                weather = state.currentWeather?.cityName ?: "unknow",
                onSearch = {
                    navigator?.push(
                        Search_Screen()
                    )
                }
            )
        },
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
    state: WeatherState,
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
                    OverallStatices(weather = state.currentWeather)

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

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    val mockWeather = Weather(
        cityName = "San Francisco",
        region = "California",
        country = "United States",
        temperatureC = 22,
        conditionText = "Partly cloudy",
        conditionIconUrl = "//cdn.weatherapi.com/weather/64x64/day/116.png",
        windKph = 15.5,
        humidity = 65,
        forcastday = listOf(
            HourlyItem(
                time = "2024-01-15 12:00",
                tempC = 22f,
                icon = "//cdn.weatherapi.com/weather/64x64/day/116.png",
                conditionText = "Partly cloudy"
            ),
            HourlyItem(
                time = "2024-01-15 13:00",
                tempC = 24f,
                icon = "//cdn.weatherapi.com/weather/64x64/day/113.png",
                conditionText = "Sunny"
            ),
            HourlyItem(
                time = "2024-01-15 14:00",
                tempC = 23f,
                icon = "//cdn.weatherapi.com/weather/64x64/day/119.png",
                conditionText = "Cloudy"
            )
        )
    )
    
    val mockState = WeatherState(
        isLoading = false,
        currentWeather = mockWeather,
        hourlyWeather = mockWeather.forcastday,
        error = null,
        searchWeatherCity = "San Francisco"
    )
    
    WeatherScreen(
        state = mockState,
        onAction = {}
    )
}

