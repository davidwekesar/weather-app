package com.example.weatherapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
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

    val locations: LiveData<List<Location>> =
        Transformations.map(database.locationDao.getLocations()) {
            it.asDomainModel()
        }

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
}