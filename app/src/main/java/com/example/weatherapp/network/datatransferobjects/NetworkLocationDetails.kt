package com.example.weatherapp.network.datatransferobjects

import com.example.weatherapp.domain.LocationDetails
import com.squareup.moshi.Json

data class NetworkLocationDetails(
    @Json(name = "EpochTime")
    val epochTime: Long,

    @Json(name = "WeatherText")
    val weatherText: String,

    @Json(name = "WeatherIcon")
    val weatherIcon: Int?,

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

fun List<NetworkLocationDetails>.asDomainModel(): List<LocationDetails> {
    return map { networkLocationDetails ->
        with(networkLocationDetails) {
            LocationDetails(
                epochTime = epochTime,
                weatherText = weatherText,
                weatherIcon = weatherIcon,
                temperature = temperature.metric.value.toInt(),
                humidity = humidity,
                dewPoint = dewPoint.metric.value.toInt(),
                wind = wind.speed.metric.value.toInt(),
                visibility = visibility.metric.value.toInt(),
                pressure = pressure.metric.value.toInt()
            )
        }
    }
}
