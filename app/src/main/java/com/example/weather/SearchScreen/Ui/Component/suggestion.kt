package com.example.weather.SearchScreen.Ui.Component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weather.SearchScreen.Ui.SearchScreenUiState

@Composable
fun AutoSuggestion(
    suggestion : SearchScreenUiState ,
    onValue :(String) -> Unit
){
   Column(
       modifier = Modifier.fillMaxSize()
   ) {
       TextField(
           value = suggestion.searchQuery,
           onValueChange = onValue,
           modifier = Modifier.fillMaxWidth().padding(16.dp),
           placeholder = { Text("Search city...") }
       )
       
       LazyColumn(
           modifier = Modifier.fillMaxWidth().weight(1f)
       ) {
           items(items = suggestion.autoSuggestion){ item ->
               Card(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(horizontal = 16.dp, vertical = 8.dp)
               ) {
                   Column(modifier = Modifier.padding(16.dp)) {
                       Text(text = item?.name ?: "", style = MaterialTheme.typography.titleMedium)
                       Text(text = "${item?.region}, ${item?.country}", style = MaterialTheme.typography.bodySmall)
                   }
               }
           }
       }
   }
}