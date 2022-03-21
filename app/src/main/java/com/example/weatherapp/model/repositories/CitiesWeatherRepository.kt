package com.example.weatherapp.model.repositories

import com.example.weatherapp.model.data.models.CityWeather
import com.example.weatherapp.model.data.remote.AccuWeather

class CitiesWeatherRepository {
    private val accuWeatherService = AccuWeather.accuWeatherService

    suspend fun fetchCitiesWeatherList(): List<CityWeather> =
        accuWeatherService.getCitiesWeatherList()
}