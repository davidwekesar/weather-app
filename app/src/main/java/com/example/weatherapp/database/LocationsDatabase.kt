package com.example.weatherapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseLocation::class], version = 1)
abstract class LocationsDatabase: RoomDatabase() {
    abstract val locationDao: LocationDao
}

private lateinit var INSTANCE: LocationsDatabase

fun getDatabase(context: Context): LocationsDatabase {
    synchronized(LocationsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                LocationsDatabase::class.java,
                "locations"
            ).build()
        }
    }
    return INSTANCE
}