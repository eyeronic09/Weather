package com.example.weather.HomeScreen.UI_Presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
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
    onSetting: () -> Unit,
    onRefresh : () -> Unit
) {
    TopAppBar(
        title = {
            Text(weather)
        },
        actions = {
            IconButton(onClick = onSearch) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Location",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
            IconButton(onClick = onSetting) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Setting",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
            IconButton(onClick = onRefresh) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "refresh",
                )
            }
        }
    )
}