package com.example.weather.HomeScreen.UI_Presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.domain.model.HourlyItem

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
 fun verticalCard(hourly: HourlyItem) {
    Column(modifier = Modifier.padding(all =8.dp)){
        Text(text = hourly.time)
        HorizontalDivider(
            modifier = Modifier,
            thickness = 2.dp,
            color = Color.Transparent
        )
        Text(text = hourly.conditionText)
        HorizontalDivider(
            modifier = Modifier,
            thickness = 2.dp,
            color = Color.Transparent
        )
        Text(text = hourly.tempC.toString())
    }
}


@Preview(showBackground = true)
@Composable
fun HourlyFourCastCardPreview() {
    val sampleHourly = listOf(
        HourlyItem(
            time = "12:00",
            tempC = 25.0f,
            conditionText = "Clear"
        ),
        HourlyItem(
            time = "15:00",
            tempC = 28.0f,
            conditionText = "Sunny"
        ),
        HourlyItem(
            time = "18:00",
            tempC = 23.0f,
            conditionText = "Cloudy"
        ),
        HourlyItem(
            time = "21:00",
            tempC = 18.0f,
            conditionText = "Rainy"
        )
    )
    HourlyFourCastCard(hourly = sampleHourly)
}