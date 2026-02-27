package com.example.weather.HomeScreen.UI_Presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.example.weather.domain.model.Weather

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarHeader(
    weather: String,
    onSearch: () -> Unit,
    onSetting: () -> Unit
) {
    TopAppBar(
        title = {
            Text(weather)
        },
        actions = {
            IconButton(onClick = onSearch) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search Location",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
            IconButton(onClick = onSetting) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Setting",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    )
}