package com.example.weather.core.Di

import com.example.weather.HomeScreen.UI_Presentation.HomeScreenVM
import com.example.weather.SettingScreen.UI_Layer.SettingVM
import com.example.weather.SettingScreen.domain.repository.HomeLocationPrefRepository
import com.example.weather.HomeScreen.data.remote.Api.WeatherApi
import com.example.weather.HomeScreen.data.remote.Api.WeatherApiImpl
import com.example.weather.HomeScreen.data.remote.Repository.WeatherRepositoryImpl
import com.example.weather.HomeScreen.domain.repository.WeatherRepository
import com.example.weather.SearchScreen.Ui.SearchScreenVM
import com.example.weather.SearchScreen.Data.Remote.autoSearchApi.AutoCompleteApi
import com.example.weather.SearchScreen.Data.Remote.autoSearchApi.AutoSearchImpl
import com.example.weather.SearchScreen.Data.Remote.Repository.AutoSearchRepositoryImpl
import com.example.weather.SearchScreen.Domain.Repository.AutoSearchRepository
import com.example.weather.core.NetworkClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Single instance of the HttpClient from your NetworkClient object
    single { NetworkClient.client }

    // Weather API dependencies
    single<WeatherApi> { WeatherApiImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }

    // Location preferences repository
    single<HomeLocationPrefRepository> { HomeLocationPrefRepository(get()) }

    // Search API dependencies
    single<AutoCompleteApi> { AutoSearchImpl(get()) }
    single<AutoSearchRepository> { AutoSearchRepositoryImpl(get()) }

    // ViewModels
    viewModel { HomeScreenVM(get(), get()) }
    viewModel { SettingVM(get(), get()) }
    viewModel { SearchScreenVM(get()) }
}
