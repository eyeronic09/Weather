package com.example.weather.HomeScreen.UI_Presentation.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.domain.model.HourlyItem
import com.example.weather.ui.theme.WeatherTheme
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HourlyFourCastCard(hourly: List<HourlyItem>) {

    val sdf = remember { SimpleDateFormat("HH:mm", Locale.ROOT) }
    val currentTime = sdf.format(System.currentTimeMillis())

    // Split the list into two parts:
    // 1. The elements whose time is greater than the current time.
    // 2. The remaining elements.
    val (match) = hourly.partition { hourlyItem ->
        val timeString = hourlyItem.time.substring(11, 16)
        Log.d("lol" , hourlyItem.toString())
        timeString > currentTime

    }

    OutlinedCard(modifier = Modifier) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Hourly Forecast",
            style = MaterialTheme.typography.headlineMedium
        )
        LazyRow(modifier = Modifier, horizontalArrangement = Arrangement.SpaceEvenly) {
            items(match) { hourlyItem ->
                hourlyVerticalCard(hourly = hourlyItem)
            }
        }
    }
}


@Composable
private fun hourlyVerticalCard(hourly: HourlyItem) {

    Column(modifier = Modifier
        .padding(all = 16.dp)
        .height(220.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Extract only the hh:mm part from the time string
        val timeString = hourly.time.substring(11, 16)
        Text(
            text = timeString
        )
        WeatherIcon(
            iconUrl = hourly.icon,
            contentDescription = hourly.icon,
        )
        Text(text = hourly.tempC.toString())
        Log.d("https" , hourly.icon)
    }
}




@Preview(showBackground = true)
@Composable
private fun hourlyVerticalCardPreview() {
    val sampleHourlyItem = HourlyItem(
        time = "2024-03-20 10:00",
        tempC = 25.5f,
        icon = "//cdn.weatherapi.com/weather/64x64/day/113.png",
        conditionText = "Sunny"
    )
    WeatherTheme {
        hourlyVerticalCard(hourly = sampleHourlyItem)
    }
}