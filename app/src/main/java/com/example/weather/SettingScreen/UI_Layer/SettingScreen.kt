package com.example.weather.SettingScreen.UI_Layer

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import com.example.weather.SettingScreen.compontent.TemporalUnit
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
    modifier: Modifier,
    state: SettingScreenUiState,
    onAction: (SettingLocationEvent) -> Unit
) {
    when {
        state.error.isNotEmpty() -> {
            Text(text = state.error)
        }

        else -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Text(text = "Home Location: ${state.HomeLocation}")
                TemporalUnit(
                    options = state.options,
                    selectedOption = state.selectedTempUnit,
                    onOptionSelected = { it ->
                        onAction(SettingLocationEvent.SetTempUnit(it))

                    }
                )
                insertLocation(
                    currentQuery = state.currentQuery,
                    onQueryChange = {
                        onAction(SettingLocationEvent.QueryChange(it))
                    },
                    isVisible = state.ShowPOP,
                    autoComplete = state.autoComplete,
                    onItemSavedToClick = { autoComplete ->
                        onAction(SettingLocationEvent.SetLocation(onLocationChange = autoComplete.name))
                    },
                    onDismiss = {
                        onAction(SettingLocationEvent.HideThatPopUp)
                    }
                )
            }
        }
    }
}
