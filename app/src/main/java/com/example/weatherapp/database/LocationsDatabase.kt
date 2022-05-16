package com.example.weatherapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DatabaseLocation::class], version = 1, exportSchema = false)
abstract class LocationsDatabase: RoomDatabase() {
    abstract val locationDao: LocationDao
}