package com.example.weather.SettingScreen.UI_Layer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(viewModel: SettingVM = koinViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val navigator = LocalNavigator.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Settings",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navigator?.pop() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(SettingEvent.ShowAddLocationPopUp)
                },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
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
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Home Location Section
        SettingSectionHeader(
            icon = MaterialSymbolsLocation_home,
            title = "Home Location"
        )

        Text(
            text = state.homeLocation,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 32.dp, top = 4.dp, bottom = 16.dp)
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 12.dp),
            thickness = 0.5.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )

        // Temperature Unit Section
        SettingSectionHeader(
            icon = Icons.Default.Thermostat,
            title = "Temperature Unit"
        )

        Spacer(modifier = Modifier.height(12.dp))

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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
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

@Composable
fun SettingSectionHeader(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String
) {
    Row(
        verticalAlignment = CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 12.dp)
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
