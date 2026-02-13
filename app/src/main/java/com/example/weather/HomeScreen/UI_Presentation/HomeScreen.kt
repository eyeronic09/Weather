package com.example.weather.HomeScreen.UI_Presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.weather.HomeScreen.UI_Presentation.component.MainHeaderTemp
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
class HomeScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewmodel = koinViewModel<HomeScreenVM>()
        val uiState by viewmodel.uiState.collectAsStateWithLifecycle()

        Scaffold(
            topBar = {
                uiState.weather?.let {
                    TopAppBar(
                        title = {
                            Text(text = it.cityName)
                        }
                    )
                }
            }
        ) { innerPadding ->
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                stickyHeader {
                    uiState.weather?.let { MainHeaderTemp(weather = it) }
                }

                item {

                }
            }
        }

    }
}


