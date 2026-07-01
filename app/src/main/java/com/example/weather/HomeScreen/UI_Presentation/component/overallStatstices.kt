package com.example.weather.HomeScreen.UI_Presentation.component

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.domain.model.Weather

@Composable
fun OverallStatices(weather: Weather, isTemp: Boolean = true) {
    val stats = listOf<@Composable () -> Unit>(
        {
            FeelsLikeCard(
                "feels like",
                value = if (isTemp) "${weather.feelsLikeC}°C" else "${weather.feelsLikeF}°F"
            )
        },
        {
            HeatIndexCard(
                "Heat index ",
                value = if (isTemp) "${weather.heatIndexC}°C" else "${weather.heatIndexF}°F"
            )
        },
        {
            CloudCard(
                "Cloud",
                value = weather.cloud.toString(),
            )
        }
    )

    val showTheIcon = remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = showTheIcon.value,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically(),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            WeatherIcon(
                animation = weather.animation,
                modifier = Modifier.size(120.dp)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val gridState = rememberLazyGridState()

        LaunchedEffect(
            key1 = gridState
        ) {
            var previousIndex = 0
            snapshotFlow {
                gridState.firstVisibleItemIndex to
                        gridState.firstVisibleItemScrollOffset
            }.collect { (index, _) ->
                val isScrolled = previousIndex > index || index != 0

                previousIndex = index
                showTheIcon.value = !isScrolled
                Log.d("showTheIconBoolean", showTheIcon.toString())
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            state = gridState

        ) {
            item(
                span = { GridItemSpan(currentLineSpan = 2) }
            ) {
                HourlyFourCastCard(
                    hourlyForecasts = weather.forecastDays.flatMap { it.hourlyForecasts },
                    isTemp = isTemp,
                )
            }
            items(
                stats,
            ) {
                it()
            }
        }
    }

}

@Composable
fun FeelsLikeCard(title: String, value: String) {
    WeatherStatCard(
        title = title,
        value = value,
        icon = Icons.Default.Thermostat,
        contentDescription = "Feels Like"
    )
}

@Composable
fun HeatIndexCard(title: String, value: String) {
    WeatherStatCard(
        title = title,
        value = value,
        icon = Icons.Default.WbSunny,
        contentDescription = "Heat Index"
    )
}

@Composable
fun CloudCard(title: String, value: String) {
    WeatherStatCard(
        title = title,
        value = value,
        icon = Icons.Default.Cloud,
        contentDescription = "Cloud"
    )
}

@Composable
fun WeatherStatCard(
    title: String,
    value: String,
    icon: ImageVector,
    contentDescription: String?
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.fillMaxWidth()
            )
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

