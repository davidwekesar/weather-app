package com.example.weatherapp.domain

data class Location(
    val locationKey: String,
    val city: String,
    val country: String,
    val epochTime: Long,
    val weatherText: String,
    val weatherIcon: Int?,
    val temperature: Int,
    var isFavorite: Boolean
)

data class LocationDetails(
    val epochTime: Long,
    val weatherText: String,
    val weatherIcon: Int?,
    val temperature: Int,
    val humidity: Int,
    val dewPoint: Int,
    val wind: Int,
    val visibility: Int,
    val pressure: Int
)