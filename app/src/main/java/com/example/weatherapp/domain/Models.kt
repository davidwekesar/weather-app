package com.example.weatherapp.domain

data class Location(
    val locationKey: String,
    val city: String,
    val country: String,
    val epochTime: Long,
    val temperature: Int,
    var isFavorite: Boolean
)