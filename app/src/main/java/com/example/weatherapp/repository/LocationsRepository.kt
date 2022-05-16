package com.example.weatherapp.repository

import com.example.weatherapp.database.LocationDao
import com.example.weatherapp.database.LocationsDatabase
import com.example.weatherapp.database.asDomainModel
import com.example.weatherapp.domain.Location
import com.example.weatherapp.domain.LocationDetails
import com.example.weatherapp.network.AccuWeather
import com.example.weatherapp.network.datatransferobjects.asDatabaseModel
import com.example.weatherapp.network.datatransferobjects.asDomainModel
import com.example.weatherapp.network.datatransferobjects.asSubDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class LocationsRepository @Inject constructor(private val locationDao: LocationDao) {

    suspend fun getAllLocations(): List<Location> =
        locationDao.getAllLocations().asDomainModel()

    suspend fun searchLocation(query: String): List<Location> =
        locationDao.search(query).asDomainModel()

    suspend fun refreshLocationsList() {
        withContext(Dispatchers.IO) {
            val locations = AccuWeather.accuWeatherService.fetchLocationsList()
            Timber.d("refreshLocationsList: Success!")
            if (locationDao.getAllLocations().isNotEmpty()) {
                Timber.d("refreshLocationList: Update")
                locationDao.updateList(locations.asSubDatabaseModel())
            } else {
                Timber.d("refreshLocationList: Refresh")
                locationDao.insertAll(locations.asDatabaseModel())
            }
        }
    }

    suspend fun getFavoriteLocation(): Location {
        return if (locationDao.getFavoriteLocation() != null) {
            locationDao.getFavoriteLocation()!!.asDomainModel()
        } else {
            locationDao.getLocation().asDomainModel()
        }
    }

    suspend fun fetchLocationDetails(locationKey: String): List<LocationDetails> =
        AccuWeather.accuWeatherService.fetchLocationDetails(locationKey).asDomainModel()

    suspend fun updateLocation(isFavorite: Boolean, locationKey: String): Int =
        locationDao.updateLocation(isFavorite, locationKey)
}