package com.example.weatherapp.model.repositories

import com.example.weatherapp.model.data.models.LocationData
import com.example.weatherapp.model.data.models.LocationListItem
import com.example.weatherapp.model.data.remote.AccuWeather

class CitiesWeatherRepository {
    private val accuWeatherService = AccuWeather.accuWeatherService

    suspend fun fetchCitiesWeatherList(): List<LocationListItem> =
        accuWeatherService.fetchCitiesWeatherList()

    suspend fun fetchLocationWeatherData(locationKey: String): List<LocationData> =
        accuWeatherService.fetchLocationWeatherData(locationKey)
}