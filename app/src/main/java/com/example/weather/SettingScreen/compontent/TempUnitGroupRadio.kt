package com.example.weather.SettingScreen.compontent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun TemperatureSelector( options: List<String>, selectedOption : String, onOptionSelected :(String) -> Unit) {
    Column(modifier = Modifier.clip(RoundedCornerShape(8.dp))){

        options.forEach { option ->
            Row(
                verticalAlignment = CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.onSecondary)
                    .clickable { onOptionSelected(option) }
                    .padding(8.dp)
            ) {
                RadioButton(
                    modifier = Modifier,
                    selected = selectedOption == option,
                    onClick = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(text = option)
            }
        }
    }
}