package com.example.weatherapp.repository

import com.example.weatherapp.database.DatabaseLocation
import com.example.weatherapp.database.LocationsDatabase
import com.example.weatherapp.database.asDomainModel
import com.example.weatherapp.domain.Location
import com.example.weatherapp.network.datatransferobjects.NetworkLocationDetails
import com.example.weatherapp.network.AccuWeather
import com.example.weatherapp.network.datatransferobjects.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class LocationsRepository(private val database: LocationsDatabase) {

    suspend fun getAllLocations(): List<Location> =
        database.locationDao.getAllLocations().asDomainModel()

    suspend fun searchLocation(query: String): List<Location> =
        database.locationDao.search(query).asDomainModel()

    suspend fun refreshLocationsList() {
        withContext(Dispatchers.IO) {
            val locations = AccuWeather.accuWeatherService.fetchLocationsList()
            Timber.d("refreshLocationsList: Success!")
            Timber.d("Locations: $locations")
            database.locationDao.insertAll(locations.asDatabaseModel())
        }
    }

    suspend fun fetchLocationWeatherData(locationKey: String): List<NetworkLocationDetails> =
        AccuWeather.accuWeatherService.fetchLocationData(locationKey)

    suspend fun updateLocation(isFavorite: Boolean, locationKey: String): Int =
        database.locationDao.update(isFavorite, locationKey)
}