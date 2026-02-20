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

    // Refactored code to calculate the current time in minutes
    val sdf = remember { SimpleDateFormat("HH:mm", Locale.ROOT) }
    val currentTime = sdf.format(System.currentTimeMillis()) // Get the current time as a string in the format "HH:mm"

    // Convert the current time string to minutes
    val currentMinutes = currentTime.split(":").let {
        // Split the string into hours and minutes
        it[0].toInt() * 60 + it[1].toInt() // Convert hours to minutes and add to minutes
    }
    Log.d("currentMinutes" , currentMinutes.toString()) // Log the current time in minutes

    // Filter the list of hourly items to only include items that are in the future based on the current time
    val upcomingForCast = hourly.filter { hourlyItem ->
        val timeString = hourlyItem.time.substring(11, 16) // Extract the time string from the item

        val hourlyMinutes = timeString.split(":").let {
            it[0].toInt() * 60 + it[1].toInt() // Convert hours to minutes and add to minutes
        }
         Log.d("upcoming" , "${hourlyMinutes} -> " )

        // Check if the item is in the future based on the current time
        hourlyMinutes >= currentMinutes.also {
            Log.d("upcoming" , "${hourlyMinutes} checking to ${currentMinutes} and currentTime ${timeString} ")
        }
    }
    //note decided to usw this yet
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