package com.example.weather.SettingScreen.domain.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.weather.SettingScreen.data.DataStoreKeys
import com.example.weather.SettingScreen.data.locationPrefs
import com.example.weather.SettingScreen.data.tempUnit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingPrefRepository(private val context: Context) {

    suspend fun saveDefaultLocation(location : String){
        context.locationPrefs.edit { preferences ->
            preferences[DataStoreKeys.defaultLocation] = location
        }
    }

     fun readDefaultLocation(): Flow<String?> {
        return context.locationPrefs.data.map { value ->
            value[DataStoreKeys.defaultLocation]
        }
    }

    suspend fun saveDefaultTempUnit(tempC: String){
        context.tempUnit.edit {
            it[DataStoreKeys.tempUnit] = tempC
        }
    }

    fun readDefaultTempUnit(): Flow<String?> {
        return context.locationPrefs.data.map { value ->
            value[DataStoreKeys.tempUnit]
        }
    }


}