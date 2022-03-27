package com.example.weatherapp.network.datatransferobjects

import com.example.weatherapp.database.DatabaseLocation
import com.example.weatherapp.database.SubDatabaseLocation
import com.squareup.moshi.Json

data class Location(
    @Json(name = "Key")
    val locationKey: String,

    @Json(name = "LocalizedName")
    val city: String,

    @Json(name = "Country")
    val country: Country,

    @Json(name = "EpochTime")
    val epochTime: Long,

    @Json(name = "Temperature")
    val temperature: Temperature
)

data class Country(
    @Json(name = "LocalizedName")
    val name: String
)

data class Temperature(
    @Json(name = "Metric")
    val metric: Metric
)

data class Metric(
    @Json(name = "Value")
    val value: Double
)

fun List<Location>.asDatabaseModel(): List<DatabaseLocation> {
    return map { location ->
        with(location) {
            DatabaseLocation(
                locationKey = locationKey,
                city = city,
                country = country.name,
                epochTime = epochTime,
                temperature = temperature.metric.value.toInt()
            )
        }
    }
}

fun List<Location>.asSubDatabaseModel(): List<SubDatabaseLocation> {
    return map { location ->
        with(location) {
            SubDatabaseLocation(
                locationKey = locationKey,
                city = city,
                country = country.name,
                epochTime = epochTime,
                temperature = temperature.metric.value.toInt()
            )
        }
    }
}
