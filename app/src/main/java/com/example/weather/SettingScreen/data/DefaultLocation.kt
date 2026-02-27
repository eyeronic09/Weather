package com.example.weather.SettingScreen.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.locationPrefs: DataStore<Preferences> by preferencesDataStore(name = "home_location")


object DataStoreKeys {
    val defaultLocation = stringPreferencesKey("Home_Locations")

}