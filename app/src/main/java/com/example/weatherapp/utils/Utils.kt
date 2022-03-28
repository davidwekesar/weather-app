package com.example.weatherapp.utils

import com.example.weatherapp.R
import java.text.SimpleDateFormat
import java.util.*

private const val MILLIS_IN_A_SECOND = 1_000

fun convertToDate(epochTime: Long): String {
    val date = Date(epochTime * MILLIS_IN_A_SECOND)
    val simpleDateFormat = SimpleDateFormat("EEE, d MMM, yyyy", Locale.getDefault())
    return simpleDateFormat.format(date)
}

fun convertToTime(epochTime: Long): String {
    val date = Date(epochTime * MILLIS_IN_A_SECOND)
    val simpleDateFormat = SimpleDateFormat("h:mm aaa", Locale.getDefault())
    return simpleDateFormat.format(date)
}

fun concatLocationName(city: String, country: String): String {
    return "$city, $country"
}

fun formatTempString(temperature: Int): String {
    return "$temperature\u00B0"
}

fun getIconResource(weatherIcon: Int?): Int {
    return if (weatherIcon != null) {
        when(weatherIcon) {
            in 1..6 -> R.drawable.ic_sunny_140
            in 7..11 -> R.drawable.ic_cloudy_140
            in 13..14 -> R.drawable.ic_partly_cloudy_with_showers_140
            15 -> R.drawable.ic_thunderstorm_140
            in 33..37 -> R.drawable.ic_clear_night_140
            else -> R.drawable.ic_sunny_140
        }
    } else {
        R.drawable.ic_sunny_140
    }
}