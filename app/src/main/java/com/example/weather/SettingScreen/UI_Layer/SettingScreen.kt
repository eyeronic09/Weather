package com.example.weather.SettingScreen.UI_Layer

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddHome
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
fun SettingScreen(
    viewModel: SettingVM = koinViewModel(),
) {
    val state by viewModel.UiState.collectAsStateWithLifecycle()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(SettingLocationEvent.ShowThatAddPopUp)
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
        Log.d("shavedHomeLocation", state.HomeLocation)
    }
}

@Composable
fun SettingContent(
    state: SettingScreenUiState,
    onAction: (SettingLocationEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row {
            Text(
                text = "Home Location" ,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Icon(
                modifier = Modifier.align(CenterVertically),
                imageVector = MaterialSymbolsLocation_home,
                contentDescription = "Home Location Icon"
            )

        }


        Text(
            text = state.HomeLocation ,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Temperature Unit",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        TemperatureSelector(
            options = state.options,
            selectedOption = state.selectedTempUnit,
            onOptionSelected = { unit ->
                onAction(SettingLocationEvent.SetTempUnit(unit))
            }
        )

        insertLocation(
            currentQuery = state.currentQuery,
            onQueryChange = { query ->
                onAction(SettingLocationEvent.QueryChange(query))
            },
            isVisible = state.ShowPOP,
            autoComplete = state.autoComplete,
            onItemSavedToClick = { item ->
                onAction(SettingLocationEvent.SetLocation(item.name))
            },
            onDismiss = {
                onAction(SettingLocationEvent.HideThatPopUp)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    SettingContent(
        state = SettingScreenUiState(
            HomeLocation = "London",
            selectedTempUnit = "C"
        ),
        onAction = {}
    )
}
