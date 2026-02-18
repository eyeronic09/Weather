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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.domain.model.HourlyItem
import com.example.weather.ui.theme.WeatherTheme

@Composable
fun HourlyFourCastCard(hourly: List<HourlyItem>) {
    OutlinedCard(modifier = Modifier) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Hourly Forecast",
            style = MaterialTheme.typography.headlineMedium
        )
        LazyRow(modifier = Modifier, horizontalArrangement = Arrangement.SpaceEvenly) {
            items(hourly) { hourlyItem ->
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

        Text(
            // Added a safety check to avoid StringIndexOutOfBoundsException
            // when the time string is shorter than 11 characters.
            text = if (hourly.time.length >= 11) hourly.time.substring(11) else hourly.time
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
        // Updated preview data to match the expected "yyyy-MM-dd HH:mm" format
        // which avoids the StringIndexOutOfBoundsException in the preview.
        time = "2024-03-20 10:00",
        tempC = 25.5f,
        icon = "//cdn.weatherapi.com/weather/64x64/day/113.png",
        conditionText = "Sunny"
    )
    WeatherTheme {
        hourlyVerticalCard(hourly = sampleHourlyItem)
    }
}
