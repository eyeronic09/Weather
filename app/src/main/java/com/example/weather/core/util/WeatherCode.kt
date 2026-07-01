package com.example.weather.core.util

import androidx.annotation.DrawableRes
import com.example.weather.R

enum class WeatherAnimation {
    Sunny,
    PartlyCloudy,
    Cloudy,
    Fog,
    LightRain,
    HeavyRain,
    Thunderstorm,
    Snow,

}


@DrawableRes
fun getWeatherGifRes(animation: WeatherAnimation): Int {
    return when (animation) {
        WeatherAnimation.Sunny -> R.drawable.sun_ezgif_com_gif_maker
        WeatherAnimation.LightRain, WeatherAnimation.HeavyRain -> R.drawable.rain_ezgif_com_gif_maker
        WeatherAnimation.Snow -> R.drawable.snow_ezgif_com_gif_maker
        WeatherAnimation.Cloudy -> R.drawable.cloudy_ezgif_com_gif_maker
        WeatherAnimation.Thunderstorm -> R.drawable.storm_ezgif_com_gif_maker
        WeatherAnimation.PartlyCloudy -> R.drawable.cloudy_ezgif_com_gif_maker
        WeatherAnimation.Fog -> R.drawable.foggy_ezgif_com_gif_maker
    }
}