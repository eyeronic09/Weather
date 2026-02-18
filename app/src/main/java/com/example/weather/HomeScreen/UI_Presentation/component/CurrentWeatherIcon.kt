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
import com.example.weather.domain.model.Weather

@Composable
fun WeatherIcon(
    iconUrl: String,
) {
    val finalUrl = if (iconUrl.startsWith("https")) {
        iconUrl.replace("64x64" , "128x128")
    } else {
        "https:$iconUrl".replace("64x64" , "128x128")
    }

    Log.d("icon" , finalUrl)

    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(finalUrl)
            .crossfade(true)
            .build(),
        contentDescription = "Weather Icon",
        modifier = Modifier.aspectRatio(1f),
        loading = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(strokeWidth = 2.dp)
            }
        },
        error = {state ->
            Log.e("COIL_ERROR", state.result.throwable.toString())
            Icon(
                imageVector = Icons.Default.Error,
                contentDescription = "Weather icon fallback",
                modifier = Modifier.size(100.dp)
            )
        }
    )

}
