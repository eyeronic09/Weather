package com.example.weather.HomeScreen.UI_Presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.HomeScreen.domain.model.HourlyWeather

@Composable
fun HourlyForecastCard(hourlyWeatherList: List<HourlyWeather>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(hourlyWeatherList) { hourlyWeather ->
            HourlyItem(hourlyWeather = hourlyWeather)
        }
    }
}

@Composable
fun HourlyItem(hourlyWeather: HourlyWeather) {
    OutlinedCard(
        modifier = Modifier
            .width(80.dp)
            .height(120.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = hourlyWeather.time.substring(11, 16),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium
            )
            
            Text(
                text = "${hourlyWeather.temperatureC.toInt()}°",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = hourlyWeather.conditionText,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
private fun HourlyForecastCardPreview() {
    val hourlyWeatherList = listOf(
        HourlyWeather(
            temperatureC = 25F,
            time = "2026-02-13 12:00",
            conditionIconUrl = "https://example.com/icon1.png",
            conditionText = "Sunny"
        ),
        HourlyWeather(
            temperatureC = 23F,
            time = "2026-02-13 13:00",
            conditionIconUrl = "https://example.com/icon2.png",
            conditionText = "Partly Cloudy"
        ),
        HourlyWeather(
            temperatureC = 20F,
            time = "2026-02-13 14:00",
            conditionIconUrl = "https://example.com/icon3.png",
            conditionText = "Light Rain"
        )
    )
    
    HourlyForecastCard(hourlyWeatherList = hourlyWeatherList)
}