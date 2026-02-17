package com.example.weather.HomeScreen.UI_Presentation.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.weather.domain.model.HourlyItem
import com.example.weather.ui.theme.WeatherTheme

@Composable
fun HourlyFourCastCard(hourly: List<HourlyItem>) {
    OutlinedCard (modifier = Modifier){
        LazyRow(modifier = Modifier, horizontalArrangement = Arrangement.SpaceEvenly){
           items(hourly){ hourlyItem ->
                verticalCard(hourly = hourlyItem)
            }
        }
    }
}


@Composable
private fun verticalCard(hourly: HourlyItem) {
    Column(modifier = Modifier
                .padding(all = 16.dp)
                .height(220.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = hourly.time.substring(
                startIndex = 11
            )
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
private fun VerticalCardPreview() {
    val sampleHourlyItem = HourlyItem(
        time = "10:00 AM",
        tempC = 25.5f,
        icon = "☀️",
        conditionText = "Sunny"
    )
    WeatherTheme {
        verticalCard(hourly = sampleHourlyItem)
    }
}