package com.example.weather.SearchScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import org.koin.androidx.compose.koinViewModel

class Search_Screen : Screen {
    @Composable
    override fun Content() {
        SearchScreenRoute()
    }
}

@Composable
fun SearchScreenRoute(
    ViewModel: SearchScreenVM = koinViewModel()
){
    val cityQuery by ViewModel.cityQuery.collectAsStateWithLifecycle()
    val predictions by ViewModel.predictions.collectAsStateWithLifecycle()

    SearchScreen(
        cityQuery = cityQuery,
        prediection = predictions,
        onQuery = ViewModel::onQueryChange
    )

}

@Composable
fun SearchScreen(
    cityQuery: String,
    prediection: List<String>,
    onQuery :(String) -> Unit
) {
    Scaffold(

    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {

            item {
                TextField(
                    value = cityQuery,
                    onValueChange = onQuery
                )
            }
            items( items = prediection){ cityCard ->
                Text(cityCard)

            }
        }
    }
}

