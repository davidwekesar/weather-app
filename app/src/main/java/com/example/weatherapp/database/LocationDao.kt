package com.example.weatherapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocationDao {
    @Query("SELECT * FROM DatabaseLocation ORDER BY isFavorite DESC")
    fun getLocations(): LiveData<List<DatabaseLocation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(locations: List<DatabaseLocation>)
}