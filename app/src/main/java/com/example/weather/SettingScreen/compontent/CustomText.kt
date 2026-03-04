package com.example.weather.SettingScreen.compontent

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun CustomCardText(
    text: String,
    style: TextStyle ,
    modifier: Modifier = Modifier
) {
    Text(text = text, style = style, modifier = modifier.padding(8.dp))
}