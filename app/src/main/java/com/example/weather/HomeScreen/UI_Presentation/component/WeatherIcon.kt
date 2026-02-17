package com.example.weather.HomeScreen.UI_Presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage

@Composable
fun WeatherIcon(
    iconUrl: String,
    contentDescription: String?,
) {
    SubcomposeAsyncImage(
        model = "https:$iconUrl",
        contentDescription = contentDescription,
        loading = @Composable{
            // Show loading indicator
            Box(
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp
                )
            }
        },
        error = @Composable{
            Icon(
                imageVector = Icons.Default.Cloud,
                contentDescription = "Weather icon fallback",
            )
        }
    )
}