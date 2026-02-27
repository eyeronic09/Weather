package com.example.weather.SettingScreen.UI_Layer

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
fun SettingRoute(){
    SettingScreen()
}

@Composable
fun SettingScreen(
    viewModel: SettingVM = koinViewModel(),
){
    val context = LocalContext.current
    val state by viewModel.UiState.collectAsStateWithLifecycle()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(SettingLocationEvent.showThePoPUp)
            }) {
                Icon(Icons.Filled.AddLocationAlt , contentDescription = null)
            }
        }
    ) { it ->
        SettingContent(
            state = state,
            onAction = viewModel::onEvent,
            modifier = Modifier.padding(it),
            context = context
        )
    }

}


@Composable
fun SettingContent(
    modifier: Modifier,
    state : SettingScreenUiState,
    onAction: (SettingLocationEvent) -> Unit,
    context: Context
){
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        insertLocation(
            currentQuery = state.currentQuery,
            onQueryChange = {
                onAction(SettingLocationEvent.QueryChange(it))
            },

            isVisible = state.ShowPOP,
            autoComplete = state.autoComplete,
            onItemSavedToClick = { autoComplete ->
                onAction(SettingLocationEvent.SetLocation(
                    onLocationChange = autoComplete.region ,
                    context = context
                ))
            },
        )
    }
}
