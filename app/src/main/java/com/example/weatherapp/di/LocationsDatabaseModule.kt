package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.database.LocationDao
import com.example.weatherapp.database.LocationsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocationsDatabaseModule {

    @Provides
    fun provideLocationDao(database: LocationsDatabase): LocationDao = database.locationDao

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): LocationsDatabase {
        return Room.databaseBuilder(
            appContext,
            LocationsDatabase::class.java,
            "locations"
        ).build()
    }
}