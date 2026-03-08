package com.example.weather.HomeScreen.UI_Presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WindPower
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.domain.model.Weather
import com.example.weather.ui.theme.WeatherTheme

@Composable
fun WeatherDetailsCard(
    weather: Weather, 
    isTempC: Boolean = true,
) {
    Card() {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically ) {
            
            Column {
                Text(
                    style = MaterialTheme.typography.displayMedium,
                    text = if (isTempC) {
                        "${String.format("%.1f", weather.temperatureC)} °C"
                    } else {
                        "${String.format("%.1f", weather.temperatureF)} °F"
                    }
                )

            }

            Column(modifier = Modifier.padding()) {
                Text(weather.conditionText , modifier = Modifier.padding(8.dp))
                ariQuality(weather.ariQuality)
            }
        }
    }
}

@Composable
private fun ariQuality(ariQ: Int) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val result = when(ariQ){
            1 -> {"good"}
            2 -> {"moderate"}
            3 -> {"unhealthy for sensitive people"}
            4 -> {"unhealthy"}
            5 -> {"Very unhealthy"}
            6 -> {"Hazardous"}
            else -> {
                "error"
            }
        }
        Text(text = result)
    }

}

@Preview(showBackground = true)
@Composable
fun WeatherDetailsCardPreview() {
    val sampleWeather = Weather(
        "London", temperatureC = 25.0,
        temperatureF = 77.0,
        conditionText = "Sunny",
        conditionIconUrl = "//cdn.weatherapi.com/weather/64x64/day/113.png",
        windKph = 10.0,
        humidity = 50,
        forecastDays = emptyList(),
        uv = 2,
        region = "re",
        country = "county",
        ariQuality = 3,
    )
    WeatherTheme {
        WeatherDetailsCard(weather = sampleWeather)
    }
}
