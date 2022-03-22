package com.example.weatherapp.model.data.models

import com.squareup.moshi.Json

data class LocationListItem(
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
    val metric: MetricDouble
)

data class MetricDouble(
    @Json(name = "Value")
    val value: Double
)
