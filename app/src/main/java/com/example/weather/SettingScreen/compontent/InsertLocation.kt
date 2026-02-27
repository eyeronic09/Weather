package com.example.weather.SettingScreen.compontent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weather.SearchScreen.Domain.Model.AutoComplete

@Composable
fun insertLocation(
    currentQuery : String,
    onQueryChange: (String) -> Unit,
    isVisible: Boolean = false,
    autoComplete: List<AutoComplete>,
    onItemSavedToClick :(AutoComplete) -> Unit
    ){
    if (isVisible) {
        Box(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center)
        {
            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = currentQuery,
                    onValueChange = onQueryChange
                )
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(autoComplete){ it
                    Card(
                        modifier = Modifier.fillMaxWidth().clickable{
                            onItemSavedToClick(it)
                        },

                    ) {
                        Text(text = it.region)
                    }
                }

            }

        }
    }
}