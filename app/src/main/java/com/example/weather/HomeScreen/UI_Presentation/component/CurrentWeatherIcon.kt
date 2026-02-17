package com.example.weather.HomeScreen.UI_Presentation.component


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.weather.domain.model.Weather

@Composable
fun getWeatherIcon(weather: Weather) {
    SubcomposeAsyncImage(
        modifier = Modifier.size(220.dp).fillMaxWidth(),
        model = ImageRequest.Builder(LocalContext.current)
            .data("https:${weather.conditionIconUrl}")
            .crossfade(true)
            .build(),
        contentDescription = "Weather Icon",
        loading = {
            Box(
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp
                )
            }
        },
        error = {
            Icon(
                imageVector = Icons.Default.Cloud,
                contentDescription = "Weather icon fallback",
                modifier = Modifier.size(100.dp)
            )
        }
    )
}