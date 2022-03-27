package com.example.weatherapp.repository

import com.example.weatherapp.database.LocationsDatabase
import com.example.weatherapp.database.asDomainModel
import com.example.weatherapp.domain.Location
import com.example.weatherapp.network.datatransferobjects.NetworkLocationDetails
import com.example.weatherapp.network.AccuWeather
import com.example.weatherapp.network.datatransferobjects.asDatabaseModel
import com.example.weatherapp.network.datatransferobjects.asSubDatabaseModel
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
            if (database.locationDao.getAllLocations().isNotEmpty()) {
                Timber.d("refreshLocationList: Update")
                database.locationDao.updateList(locations.asSubDatabaseModel())
            } else {
                Timber.d("refreshLocationList: Refresh")
                database.locationDao.insertAll(locations.asDatabaseModel())
            }
        }
    }

    suspend fun fetchLocationWeatherData(locationKey: String): List<NetworkLocationDetails> =
        AccuWeather.accuWeatherService.fetchLocationData(locationKey)

    suspend fun updateLocation(isFavorite: Boolean, locationKey: String): Int =
        database.locationDao.updateLocation(isFavorite, locationKey)
}