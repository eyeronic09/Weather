package com.example.weather.SettingScreen.domain.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.weather.SettingScreen.data.DataStoreKeys
import com.example.weather.SettingScreen.data.locationPrefs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeLocationPrefRepository(private val context: Context) {

    suspend fun saveDeafaultLocation(location : String ){
        context.locationPrefs.edit { preferences ->
            preferences[DataStoreKeys.defaultLocation] = location
        }
    }

     fun readDeafaultLocation(context: Context): Flow<String?> {
        return context.locationPrefs.data.map { value ->
            value[DataStoreKeys.defaultLocation]
        }

    }
}