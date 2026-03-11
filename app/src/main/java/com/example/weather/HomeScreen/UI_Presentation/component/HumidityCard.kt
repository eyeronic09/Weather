package com.example.weather.HomeScreen.UI_Presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.R


@Preview(showBackground = true)
@Composable
fun HumidityCardPreview() {
    HumidityCard(
        title = "Humidity",
        value = "80%"
    )
}

@Composable
fun HumidityCard(
    title : String,
    value: String
) {

    Card() {
        Column(modifier = Modifier
            .padding(16.dp)
            .aspectRatio(1f)) {
            Text(text = title)
            Text(text = value)
            Icon(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.outline_humidity_low_24),
                contentDescription = "Humidity"
            )

        }
    }
}