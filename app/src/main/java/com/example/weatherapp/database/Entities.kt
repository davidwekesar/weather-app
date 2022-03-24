package com.example.weatherapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.domain.Location

@Entity
data class DatabaseLocation(
    @PrimaryKey
    val locationKey: String,
    val city: String,
    val country: String,
    val epochTime: Long,
    val temperature: Int
) {
    var isFavorite: Boolean = false
}


fun List<DatabaseLocation>.asDomainModel(): List<Location> {
    return map {location ->
        with(location) {
            Location(
                locationKey = locationKey,
                city = city,
                country = country,
                epochTime = epochTime,
                temperature = temperature,
                isFavorite = isFavorite
            )
        }
    }
}