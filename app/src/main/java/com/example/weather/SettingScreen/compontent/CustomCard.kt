package com.example.weather.SettingScreen.compontent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults.outlinedCardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun CustomCardPreview() {
    CustomCard(
        country = "US", 
        region = "California", 
        city = "San Francisco",
        onClick = {  }
    )
}

@Composable
fun CustomCard(
    country: String,
    region: String,
    city: String,
    onClick: () -> Unit
) {
    OutlinedCard(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,

        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        ),
    ) {
        Text(text = region, style = MaterialTheme.typography.titleMedium)
        Text(text = "$country , $city"  , style = MaterialTheme.typography.titleSmall)
    }
}