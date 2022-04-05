package com.example.weatherapp.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.weatherapp.R
import com.example.weatherapp.domain.Location
import com.example.weatherapp.ui.MainActivity

private const val NOTIFICATION_ID = 0

fun NotificationManager.sendNotification(location: Location, appContext: Context) {
    val contentIntent = Intent(appContext, MainActivity::class.java)
    val contentPendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        PendingIntent.getActivity(
            appContext,
            NOTIFICATION_ID,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    } else {
        PendingIntent.getActivity(
            appContext,
            NOTIFICATION_ID,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }
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
        .setContentIntent(contentPendingIntent)
        .setPriority(NotificationCompat.PRIORITY_LOW)
        .setAutoCancel(true)

    notify(NOTIFICATION_ID, builder.build())
}