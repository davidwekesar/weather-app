package com.example.weatherapp.network.datatransferobjects

import com.example.weatherapp.database.DatabaseLocation
import com.example.weatherapp.database.SubDatabaseLocation
import com.squareup.moshi.Json

data class NetworkLocation(
    @Json(name = "Key")
    val locationKey: String,

    @Json(name = "LocalizedName")
    val city: String,

    @Json(name = "Country")
    val country: Country,

    @Json(name = "EpochTime")
    val epochTime: Long,

    @Json(name = "WeatherText")
    val weatherText: String,

    @Json(name = "WeatherIcon")
    val weatherIcon: Int?,

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

fun List<NetworkLocation>.asDatabaseModel(): List<DatabaseLocation> {
    return map { networkLocation ->
        with(networkLocation) {
            DatabaseLocation(
                locationKey = locationKey,
                city = city,
                country = country.name,
                epochTime = epochTime,
                weatherText = weatherText,
                weatherIcon = weatherIcon,
                temperature = temperature.metric.value.toInt()
            )
        }
    }
}

fun List<NetworkLocation>.asSubDatabaseModel(): List<SubDatabaseLocation> {
    return map { networkLocation ->
        with(networkLocation) {
            SubDatabaseLocation(
                locationKey = locationKey,
                city = city,
                country = country.name,
                epochTime = epochTime,
                weatherText = weatherText,
                weatherIcon = weatherIcon,
                temperature = temperature.metric.value.toInt()
            )
        }
    }
}
