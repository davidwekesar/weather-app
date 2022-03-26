package com.example.weatherapp.viewmodels

import android.app.Application
import android.text.Editable
import androidx.lifecycle.*
import com.example.weatherapp.database.getDatabase
import com.example.weatherapp.domain.Location
import com.example.weatherapp.repository.LocationsRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class LocationsViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = LocationsRepository(getDatabase(application))

    private var _locations = MutableLiveData<List<Location>>()
    val locations: LiveData<List<Location>> get() = _locations

    init {
        refreshDataFromRepository()
    }

    fun search(query: Editable?) {
        viewModelScope.launch {
            if (query.isNullOrBlank()) {
                _locations.value = repository.getAllLocations()
            } else {
                repository.searchLocation("%$query%").let { locations ->
                    _locations.value = locations
                }
            }
        }
    }

    fun updateLocation(isFavorite: Boolean, locationKey: String) {
        viewModelScope.launch {
            repository.updateLocation(isFavorite = isFavorite, locationKey = locationKey)
                .let { rows ->
                    if (rows > 0) {
                        _locations.value = repository.getAllLocations()
                    }
                }
        }
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                repository.refreshLocationsList()
                _locations.value = repository.getAllLocations()
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