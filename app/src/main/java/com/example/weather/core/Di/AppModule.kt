package com.example.weather.core.Di

import com.example.weather.HomeScreen.UI_Presentation.HomeScreenVM
import com.example.weather.HomeScreen.data.remote.Api.WeatherApi
import com.example.weather.HomeScreen.data.remote.Api.WeatherApiImpl
import com.example.weather.HomeScreen.data.remote.Repository.WeatherRepositoryImpl
import com.example.weather.HomeScreen.domain.repository.WeatherRepository
import com.example.weather.core.NetworkClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Single instance of the HttpClient from your NetworkClient object
    single { NetworkClient.client }

    // Provide WeatherApi, injecting the HttpClient
    single<WeatherApi> { WeatherApiImpl(get()) }

    // Provide WeatherRepository, injecting the WeatherApi
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }

    viewModel { HomeScreenVM(get()) }

}
