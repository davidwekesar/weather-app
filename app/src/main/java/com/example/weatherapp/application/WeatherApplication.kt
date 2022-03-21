package com.example.weatherapp.application

import android.app.Application
import com.example.weatherapp.BuildConfig
import timber.log.Timber

class WeatherApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}