package com.example.weatherapp.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.weatherapp.database.getDatabase
import com.example.weatherapp.repository.LocationsRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class LocationsViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = LocationsRepository(getDatabase(application))

    val locations = repository.locations

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                repository.refreshLocationsList()
                Timber.d("fetchCitiesWeatherList:success")
            } catch (e: Exception) {
                Timber.e(e, "fetchCitiesWeatherList:failure")
            }
        }
    }
}

class LocationsViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationsViewModel::class.java)) {
            return LocationsViewModel(application) as T
        }
        throw IllegalArgumentException("Unable to construct ViewModel")
    }
}