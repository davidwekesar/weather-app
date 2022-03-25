package com.example.weatherapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocationDao {
    @Query("SELECT * FROM DatabaseLocation ORDER BY isFavorite DESC")
    suspend fun getAllLocations(): List<DatabaseLocation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(locations: List<DatabaseLocation>)

    @Query("SELECT * FROM DatabaseLocation WHERE DatabaseLocation.city LIKE :query OR DatabaseLocation.country LIKE :query")
    suspend fun search(query: String): List<DatabaseLocation>
}