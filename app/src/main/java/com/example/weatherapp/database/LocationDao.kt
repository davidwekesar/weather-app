package com.example.weatherapp.database

import androidx.room.*

@Dao
interface LocationDao {
    @Query("SELECT * FROM DatabaseLocation ORDER BY isFavorite DESC")
    suspend fun getAllLocations(): List<DatabaseLocation>

    @Query("SELECT * FROM DatabaseLocation WHERE DatabaseLocation.city LIKE :query OR DatabaseLocation.country LIKE :query")
    suspend fun search(query: String): List<DatabaseLocation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(locations: List<DatabaseLocation>)

    @Query("UPDATE DatabaseLocation SET isFavorite = :isFavorite WHERE locationKey = :locationKey")
    suspend fun update(isFavorite: Boolean, locationKey: String): Int
}