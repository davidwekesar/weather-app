package com.example.weatherapp.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.weatherapp.database.getDatabase
import com.example.weatherapp.repository.LocationsRepository
import retrofit2.HttpException
import timber.log.Timber

class RefreshDataWorker(appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "com.example.weatherapp.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = LocationsRepository(database)

        try {
            repository.refreshLocationsList()
            Timber.d("refreshDataWorker:doWork: Run")
        } catch (e: HttpException) {
            Result.retry()
        }

        return Result.success()
    }
}