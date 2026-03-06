package com.example.weather.SettingScreen.UI_Layer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import com.example.weather.SettingScreen.compontent.MaterialSymbolsLocation_home
import com.example.weather.SettingScreen.compontent.TemperatureSelector
import com.example.weather.SettingScreen.compontent.insertLocation
import org.koin.androidx.compose.koinViewModel

class Setting_Screen : Screen {
    @Composable
    override fun Content() {
        SettingRoute()
    }
}

@Composable
fun SettingRoute() {
    SettingScreen()
}

@Composable
fun SettingScreen(viewModel: SettingVM = koinViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(SettingEvent.ShowAddLocationPopUp)
            }) {
                Icon(Icons.Filled.AddLocationAlt, contentDescription = "Add Location")
            }
        }
    ) { padding ->
        SettingContent(
            state = state,
            onAction = viewModel::onEvent,
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun SettingContent(
    state: SettingScreenUiState,
    onAction: (SettingEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = CenterVertically) {
            Icon(
                imageVector = MaterialSymbolsLocation_home,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Home Location",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Text(
            text = state.homeLocation,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 32.dp, top = 4.dp, bottom = 24.dp)
        )

        Text(
            text = "Temperature Unit",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        TemperatureSelector(
            options = state.options,
            selectedOption = state.selectedTempUnit,
            onOptionSelected = { unit ->
                onAction(SettingEvent.SetTempUnit(unit))
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (state.error != null) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        insertLocation(
            currentQuery = state.currentQuery,
            onQueryChange = { query ->
                onAction(SettingEvent.QueryChange(query))
            },
            isVisible = state.showPopUp,
            autoComplete = state.autoComplete,
            onItemSavedToClick = { item ->
                onAction(SettingEvent.SelectLocation(item.name))
            },
            onDismiss = {
                onAction(SettingEvent.DismissPopUp)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    SettingContent(
        state = SettingScreenUiState(
            homeLocation = "London",
            selectedTempUnit = "Celsius (°C)"
        ),
        onAction = {}
    )
}
