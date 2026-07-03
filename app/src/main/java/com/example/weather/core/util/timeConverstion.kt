package com.example.weather.core.util

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.to12HourFormat() : String {
    val inputParser = DateTimeFormatter.ofPattern("HH:mm")
    val outOutFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)
    return LocalTime.parse(this, inputParser).format(outOutFormatter)
}