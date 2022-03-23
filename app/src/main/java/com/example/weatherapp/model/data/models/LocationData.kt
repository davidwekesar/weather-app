package com.example.weatherapp.model.data.models

import com.squareup.moshi.Json

data class LocationData(
    @Json(name = "EpochTime")
    val epochTime: Long,

    @Json(name = "WeatherText")
    val weatherText: String,

    @Json(name = "Temperature")
    val temperature: Temperature,

    @Json(name = "RelativeHumidity")
    val humidity: Int,

    @Json(name = "DewPoint")
    val dewPoint: DewPoint,

    @Json(name = "Wind")
    val wind: Wind,

    @Json(name = "Visibility")
    val visibility: Visibility,

    @Json(name = "Pressure")
    val pressure: Pressure
)

data class DewPoint(
    @Json(name = "Metric")
    val metric: Metric
)

data class Wind(
    @Json(name = "Speed")
    val speed: Speed
)

data class Speed(
    @Json(name = "Metric")
    val metric: Metric
)

data class Visibility(
    @Json(name = "Metric")
    val metric: Metric
)

data class Pressure(
    @Json(name = "Metric")
    val metric: Metric
)
