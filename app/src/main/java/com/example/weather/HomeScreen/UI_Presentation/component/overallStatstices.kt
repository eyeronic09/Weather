package com.example.weather.HomeScreen.UI_Presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.domain.model.HourlyItem
import com.example.weather.domain.model.Weather
import com.example.weather.ui.theme.WeatherTheme

@Composable
fun OverallStatices(weather: Weather) {
    val current = mapOf(
        "Temperature" to weather.temperatureC,
        "Wind" to weather.windKph,
        "Humidity" to weather.humidity,
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)

        ) {
            item (
                span = { GridItemSpan(currentLineSpan = 2) }
            ) {
                HourlyFourCastCard( weather.forcastday)
            }

            items(current.toList()) { (title, value) ->
                statCard(
                    title = title,
                    value = value.toString()
                )
            }
        }

    }
}


@Composable
fun statCard(
    title : String,
    value: String
) {
    Card() {
        Column(modifier = Modifier
            .padding(16.dp)
            .aspectRatio(1f)) {
            Text(text = title)
            Text(text = value)

        }
    }
}


@Preview(showBackground = true)
@Composable
fun OverallStaticesPreview() {
    WeatherTheme {
        val sampleWeather = Weather(
            cityName = "New York",
            region = "NY",
            country = "USA",
            temperatureC = 25,
            conditionText = "Sunny",
            conditionIconUrl = "//cdn.weatherapi.com/weather/64x64/day/113.png",
            windKph = 15,
            humidity = 60,
            forcastday = listOf(
                HourlyItem(
                    time = "2024-01-01 12:00",
                    tempC = 25f,
                    icon = "//cdn.weatherapi.com/weather/64x64/day/113.png",
                    conditionText = "Sunny"
                ),
                HourlyItem(
                    time = "2024-01-01 13:00",
                    tempC = 26f,
                    icon = "//cdn.weatherapi.com/weather/64x64/day/116.png",
                    conditionText = "Partly cloudy"
                ),
                HourlyItem(
                    time = "2024-01-01 14:00",
                    tempC = 24f,
                    icon = "//cdn.weatherapi.com/weather/64x64/day/119.png",
                    conditionText = "Cloudy"
                ),
                HourlyItem(
                    time = "2024-01-01 14:00",
                    tempC = 24f,
                    icon = "//cdn.weatherapi.com/weather/64x64/day/119.png",
                    conditionText = "Cloudy"
                ),
                HourlyItem(
                    time = "2024-01-01 14:00",
                    tempC = 24f,
                    icon = "//cdn.weatherapi.com/weather/64x64/day/119.png",
                    conditionText = "Cloudy"
                ), HourlyItem(
                    time = "2024-01-01 14:00",
                    tempC = 24f,
                    icon = "//cdn.weatherapi.com/weather/64x64/day/119.png",
                    conditionText = "Cloudy"
                ),
                HourlyItem(
                    time = "2024-01-01 14:00",
                    tempC = 24f,
                    icon = "//cdn.weatherapi.com/weather/64x64/day/119.png",
                    conditionText = "Cloudy"
                )

            )
        )

        OverallStatices(weather = sampleWeather)
    }
}

@Preview(showBackground = true)
@Composable
fun statCardPreview(){
    statCard("12" , "wind")
}