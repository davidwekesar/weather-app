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