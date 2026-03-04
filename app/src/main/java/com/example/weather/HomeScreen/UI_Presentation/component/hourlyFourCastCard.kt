package com.example.weather.HomeScreen.UI_Presentation.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.domain.model.HourlyForecast
import com.example.weather.ui.theme.WeatherTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun HourlyFourCastCard(hourlyForecasts: List<HourlyForecast>) {
    val listState = rememberLazyListState()



    LaunchedEffect(listState) {
        snapshotFlow {
            listState.firstVisibleItemIndex
        }.collect { index->
            Log.d("currentlyIndex" , "The item key on the left is: $index")
        }
    }

    OutlinedCard(modifier = Modifier) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Hourly Forecast",
            style = MaterialTheme.typography.headlineMedium
        )
        LazyRow(modifier = Modifier, horizontalArrangement = Arrangement.SpaceEvenly, state = listState) {
            items(hourlyForecasts ,) { item ->
                hourlyVerticalCard(hourly = item)
            }
        }

    }
}

@Composable
private fun hourlyVerticalCard(hourly: HourlyForecast) {

    Column(modifier = Modifier
        .padding(all = 16.dp)
        .height(220.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val timeString = if (hourly.time.length >= 16) hourly.time.substring(11, 16) else hourly.time
        Text(
            text = timeString
        )
        WeatherIcon(
            iconUrl = hourly.icon,
            contentDescription = hourly.icon,
        )
        Text(text = hourly.tempC.toString())
    }
}

@Preview(showBackground = true)
@Composable
private fun hourlyVerticalCardPreview() {
    val sampleHourlyForecast = HourlyForecast(
        time = "2024-03-20 10:00",
        tempC = 25.5f,
        icon = "//cdn.weatherapi.com/weather/64x64/day/113.png",
        conditionText = "Sunny"
    )
    WeatherTheme {
        hourlyVerticalCard(hourly = sampleHourlyForecast)
    }
}
