package com.example.weather

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import cafe.adriel.voyager.navigator.Navigator
import com.example.weather.HomeScreen.UI_Presentation.HomeScreen
import com.example.weather.ui.theme.WeatherTheme


class MainActivity : ComponentActivity() {
    private val requestLocationPermission = registerForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        callback = { isBoolean : Boolean ->

        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        checkLocationPermission()
        setContent {
            WeatherTheme {
                Navigator(

                    screen = HomeScreen(
                        locationName = ""
                    )
                )
            }
        }
    }
    private fun checkLocationPermission() {
        val permissionStatus =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }



}


