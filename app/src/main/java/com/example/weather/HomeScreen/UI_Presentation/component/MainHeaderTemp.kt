package com.example.weather.HomeScreen.UI_Presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.HomeScreen.domain.model.Weather

@Composable
fun MainHeaderTemp(weather: Weather) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,

    ) {
        Text(
            modifier = Modifier,
            text = weather.temperatureC.toString() + " C",
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = weather.conditionText,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}




@Composable
@Preview(showBackground = true)
private fun MainHeaderTempPreview() {
    val weather = Weather(
        cityName = "Berlin",
        region = "i dont know",
        country = "Germany",
        temperatureC = 32.toFloat(),
        conditionText = "Light rain",
        conditionIconUrl = "er",
        windKph = 23F,
        humidity = 23
    )
    MainHeaderTemp(weather)
}