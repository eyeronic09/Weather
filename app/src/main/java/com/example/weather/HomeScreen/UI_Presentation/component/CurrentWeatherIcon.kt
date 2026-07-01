package com.example.weather.HomeScreen.UI_Presentation.component


import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.weather.core.util.WeatherAnimation
import com.example.weather.core.util.getWeatherGifRes
import com.example.weather.domain.model.Weather

@Composable
fun WeatherIcon(
    animation: WeatherAnimation,
    modifier : Modifier = Modifier
) {
    AsyncImage(
        model = getWeatherGifRes(animation),
        contentDescription = animation.name,
        modifier = modifier
    )
}
