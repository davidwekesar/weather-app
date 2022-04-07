package com.example.weatherapp.utils

import com.example.weatherapp.R
import java.text.SimpleDateFormat
import java.util.*

private const val MILLIS_IN_A_SECOND = 1_000

/**
 * Converts epoch time to the date format pattern: Thur, 7 Apr, 2022
 */
fun convertToDate(epochTime: Long): String {
    val date = Date(epochTime * MILLIS_IN_A_SECOND)
    val simpleDateFormat = SimpleDateFormat("EEE, d MMM, yyyy", Locale.getDefault())
    return simpleDateFormat.format(date)
}

/**
 * Converts epoch time to the time format pattern: 1:22 p.m.
 */
fun convertToTime(epochTime: Long): String {
    val date = Date(epochTime * MILLIS_IN_A_SECOND)
    val simpleDateFormat = SimpleDateFormat("h:mm aaa", Locale.getDefault())
    return simpleDateFormat.format(date)
}

/**
 * Returns a drawable resource value based on the weatherIcon argument.
 */
fun getWeatherImageResource(weatherIcon: Int?): Int {
    return if (weatherIcon != null) {
        when(weatherIcon) {
            in 1..3 -> R.drawable.ic_sunny_140
            4 -> R.drawable.ic_partly_sunny_140
            5 -> R.drawable.ic_hazy_sunshine_140
            6 -> R.drawable.ic_partly_sunny_140
            in 7..8 -> R.drawable.ic_cloudy_140
            11 -> R.drawable.ic_fog_140
            12 -> R.drawable.ic_rain_140
            in 13..14 -> R.drawable.ic_partly_cloudy_with_showers_140
            15 -> R.drawable.ic_thunderstorm_140
            in 16..17 -> R.drawable.ic_cloudy_day_storm_140
            18 -> R.drawable.ic_rain_140
            19 -> R.drawable.ic_snow_140
            in 20..21 -> R.drawable.ic_day_cloudy_snow_140
            22 -> R.drawable.ic_snow_140
            23 -> R.drawable.ic_day_cloudy_snow_140
            in 24..29 -> R.drawable.ic_rain_snow_140
            30 -> R.drawable.ic_hot_140
            31 -> R.drawable.ic_cold_140
            32 -> R.drawable.ic_windy_140
            in 33..34 -> R.drawable.ic_clear_night_140
            in 35..36 -> R.drawable.ic_cloudy_night_140
            37 -> R.drawable.ic_hazy_moonlight_140
            38 -> R.drawable.ic_cloudy_night_140
            in 39..40 -> R.drawable.ic_cloudy_night_showers_140
            in 41..42 -> R.drawable.ic_cloudy_night_storm_140
            in 43..44 -> R.drawable.ic_snowy_night_140
            else -> R.drawable.ic_sunny_140
        }
    } else {
        R.drawable.ic_sunny_140
    }
}