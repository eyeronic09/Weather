package com.example.weather.core.util

import android.content.Context
import android.util.Log
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.tasks.await

class LocationService(private val context: Context) {
    @Suppress("MissingPermission")
    suspend fun getDeviceCoordinates(): Coordinates? {
        val fussedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        val locationRequest = CurrentLocationRequest.Builder().setPriority(Priority.PRIORITY_BALANCED_POWER_ACCURACY).build()
        return try {
            val location : android.location.Location? = fussedLocationClient.getCurrentLocation(locationRequest , null).await()
            if (location != null){
                Log.d("LocationService", "Device coordinates found: Lat=${location.latitude}, Lon=${location.longitude}")
                Coordinates(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
            }else {
                Log.d("LocationService", "Device coordinates are null")
                null
            }
        } catch (e: Exception) {
            Log.d("LocationService", "Error getting location: ${e.message}")
            null
        }
    }
}