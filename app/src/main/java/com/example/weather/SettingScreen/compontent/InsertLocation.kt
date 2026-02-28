package com.example.weather.SettingScreen.compontent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.SearchScreen.Domain.Model.AutoComplete

@Composable
fun insertLocation(
    currentQuery: String,
    onQueryChange: (String) -> Unit,
    isVisible: Boolean = false,
    autoComplete: List<AutoComplete>,
    onItemSavedToClick: (AutoComplete) -> Unit,
    onDismiss: () -> Unit
) {
    if (isVisible) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = "Search Location", style = MaterialTheme.typography.headlineSmall)
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = currentQuery,
                        onValueChange = onQueryChange,
                        label = { Text("Enter city name") },
                        singleLine = true
                    )
                    
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 300.dp)
                            .padding(top = 8.dp)
                    ) {
                        items(autoComplete) { item ->
                            CustomCard(
                                country = item.country,
                                region = item.region,
                                city = item.name,
                                onClick = {
                                    onItemSavedToClick(item)
                                },
                            )
                        }
                    }
                }
            },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Dismiss")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InsertLocationPreview() {
    insertLocation(
        currentQuery = "Paris",
        onQueryChange = {},
        isVisible = true,
        autoComplete = listOf(
            AutoComplete("Paris", "France", "Europe"),
            AutoComplete("London", "England", "Europe"),
            AutoComplete("New York", "USA", "America")
        ),
        onItemSavedToClick = {},
        onDismiss = {}
    )
}