package com.example.weatherapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.Location
import com.example.weatherapp.repository.LocationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    application: Application,
    private val repository: LocationsRepository
) : AndroidViewModel(application) {

    private var _locations = MutableLiveData<List<Location>>()
    val locations: LiveData<List<Location>> get() = _locations

    init {
        getAllLocations()
    }

    fun search(query: String?) {
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
            val newFavoriteValue = !isFavorite
            repository.updateLocation(isFavorite = newFavoriteValue, locationKey = locationKey)
                .let { rows ->
                    if (rows > 0) {
                        _locations.value = repository.getAllLocations()
                    }
                }
        }
    }

    private fun getAllLocations() {
        viewModelScope.launch {
            try {
                _locations.value = repository.getAllLocations().ifEmpty {
                    repository.refreshLocationsList()
                    repository.getAllLocations()
                }
                Timber.d("fetchAllLocations:success")
            } catch (e: Exception) {
                Timber.e(e, "fetchAllLocations:failure")
            }
        }
    }
}