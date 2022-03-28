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
    val weatherText: String,
    val weatherIcon: Int?,
    val temperature: Int
) {
    var isFavorite: Boolean = false
}

data class SubDatabaseLocation(
    val locationKey: String,
    val city: String,
    val country: String,
    val epochTime: Long,
    val weatherText: String,
    val weatherIcon: Int?,
    val temperature: Int
)

fun List<DatabaseLocation>.asDomainModel(): List<Location> {
    return map { location ->
        with(location) {
            Location(
                locationKey = locationKey,
                city = city,
                country = country,
                epochTime = epochTime,
                weatherText = weatherText,
                weatherIcon = weatherIcon,
                temperature = temperature,
                isFavorite = isFavorite
            )
        }
    }
}

fun DatabaseLocation.asDomainModel(): Location {
    return with(this) {
        Location(
            locationKey = locationKey,
            city = city,
            country = country,
            epochTime = epochTime,
            weatherText = weatherText,
            weatherIcon = weatherIcon,
            temperature = temperature,
            isFavorite = isFavorite
        )
    }
}