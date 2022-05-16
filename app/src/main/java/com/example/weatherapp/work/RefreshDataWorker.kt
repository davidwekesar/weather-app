package com.example.weatherapp.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.weatherapp.R
import com.example.weatherapp.database.LocationDao
import com.example.weatherapp.repository.LocationsRepository
import com.example.weatherapp.utils.sendNotification
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class RefreshDataWorker (
    appContext: Context,
    params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    @Inject lateinit var locationDao: LocationDao

    companion object {
        const val WORK_NAME = "com.example.weatherapp.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val repository = LocationsRepository(locationDao)
        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager

        createNotificationChannel(
            applicationContext.getString(R.string.weather_notification_channel_id),
            applicationContext.getString(R.string.weather_notification_channel_name)
        )

        try {
            repository.refreshLocationsList()
            val favoriteLocation = repository.getFavoriteLocation()
            notificationManager.sendNotification(favoriteLocation, applicationContext)
            Timber.d("refreshDataWorker:doWork: Run")
        } catch (e: HttpException) {
            Result.retry()
        }

        return Result.success()
    }

    private fun createNotificationChannel(channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.YELLOW
            notificationChannel.enableVibration(true)

            val manager = applicationContext.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(notificationChannel)
        }
    }
}