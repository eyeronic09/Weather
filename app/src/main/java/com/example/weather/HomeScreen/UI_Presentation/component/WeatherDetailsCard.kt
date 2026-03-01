package com.example.weather.HomeScreen.UI_Presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.WindPower
import androidx.compose.material.icons.filled.Window
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.domain.model.Weather
import com.example.weather.ui.theme.WeatherTheme

@Composable
fun WeatherDetailsCard(
    weather: Weather, 
    isTempC: Boolean = true,
    onToggleTempUnit: () -> Unit = {}
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
                        "${weather.temperatureC} °C"
                    } else {
                        "${weather.temperatureF} °F"
                    }
                )
                
                IconButton(
                    onClick = onToggleTempUnit,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Icon(
                        Icons.Default.SwapVert,
                        contentDescription = "Toggle temperature unit",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Column(modifier = Modifier.padding()) {
                Text(weather.conditionText , modifier = Modifier.padding(8.dp))
                Windspeed(weather.windKph.toString())
            }
        }
    }
}

@Composable
private fun Windspeed(windSpeed: String) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = windSpeed)
        Icon(
            Icons.Default.WindPower,
            "wind speed",
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Preview(showBackground = true , uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun WeatherDetailsCardPreview() {
    val sampleWeather = Weather(
        cityName = "New York",
        region = "New York",
        country = "United States",
        temperatureC = 25,
        conditionText = "Sunny",
        conditionIconUrl = "",
        windKph = 15.0f,
        humidity = 60,
        forcastday = emptyList(),
        temperatureF = 0f
    )
    WeatherTheme {
        WeatherDetailsCard(weather = sampleWeather)
    }
}

