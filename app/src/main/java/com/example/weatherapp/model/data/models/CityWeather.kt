package com.example.weatherapp.model.data.models

import com.squareup.moshi.Json

data class CityWeather(
    @Json(name = "LocalizedName")
    val city: String,

    @Json(name = "Temperature")
    val temperature: Temperature
)

data class Temperature(
    @Json(name = "Metric")
    val metric: Metric
)

data class Metric(
    @Json(name = "Value")
    val value: Double,

    @Json(name = "Unit")
    val unit: String
)
