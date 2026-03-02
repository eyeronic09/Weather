package com.example.weather.SettingScreen.compontent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TemporalUnit(options: List<String>, selectedOption : String , onOptionSelected :(String) -> Unit) {
    Column {
        options.forEach { option ->
            Row(
                verticalAlignment = CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOptionSelected(option) }
                    .padding(8.dp)
            ) {
                RadioButton(
                    selected = selectedOption == option,
                    onClick = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(text = option)
            }
        }
    }
    
}