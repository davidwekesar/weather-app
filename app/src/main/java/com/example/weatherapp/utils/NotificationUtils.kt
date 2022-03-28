package com.example.weatherapp.utils

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.weatherapp.R
import com.example.weatherapp.domain.Location

private const val NOTIFICATION_ID = 0

fun NotificationManager.sendNotification(location: Location, appContext: Context) {
    val builder = NotificationCompat
        .Builder(appContext, appContext.getString(R.string.weather_notification_channel_id))
        .setSmallIcon(R.mipmap.ic_launcher_round)
        .setContentTitle(
            appContext.getString(
                R.string.notification_title,
                location.temperature,
                location.city
            )
        )
        .setContentText(location.weatherText)
        .setAutoCancel(true)

    notify(NOTIFICATION_ID, builder.build())
}