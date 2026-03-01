package com.example.weather.SettingScreen.UI_Layer

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
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
                viewModel.onEvent(SettingLocationEvent.showThePoPUp)
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
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(text = "Home Location: ${state.HomeLocation}")

        RadioButton(
            selected = ,
            onClick = TODO()
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
                onAction(SettingLocationEvent.hideThatPopUp)
            }
        )
    }
}
