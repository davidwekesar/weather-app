package com.example.weatherapp.utils

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