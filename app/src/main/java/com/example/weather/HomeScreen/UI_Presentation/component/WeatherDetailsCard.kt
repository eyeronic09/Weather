package com.example.weather.HomeScreen.UI_Presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.domain.model.Weather
import com.example.weather.ui.theme.WeatherTheme

@Composable
fun WeatherDetailsCard(
    weather: Weather, 
    isTempC: Boolean = true,
) {
    Card {
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
            Column {
                Text(
                    text = weather.conditionText,
                    modifier = Modifier.padding().fillMaxWidth(),
                    textAlign = TextAlign.End
                )
                AirQualityInfo(weather.airQuality)
            }
        }
    }
}

@Composable
private fun AirQualityInfo(airQ: Int) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val result = when(airQ) {
            1 -> "good"
            2 -> "moderate"
            3 -> "unhealthy for sensitive ppl"
            4 -> "unhealthy"
            5 -> "Very unhealthy"
            6 -> "Hazardous"
            else -> "error"
        }
        Text(
            text = "Air Quality: $result",
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            textAlign = TextAlign.End
        )
    }
}

