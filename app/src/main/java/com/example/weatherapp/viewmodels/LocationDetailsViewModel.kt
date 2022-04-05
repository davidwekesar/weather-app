package com.example.weatherapp.viewmodels

import androidx.lifecycle.*
import com.example.weatherapp.domain.LocationDetails
import com.example.weatherapp.repository.LocationsRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class LocationDetailsViewModel(
    private val locationKey: String,
    private val repository: LocationsRepository
) : ViewModel() {

    private val _locationData = MutableLiveData<List<LocationDetails>>()
    val locationDetails: LiveData<List<LocationDetails>> get() = _locationData

    init {
        fetchLocationDetails()
    }

    private fun fetchLocationDetails() {
        viewModelScope.launch {
            try {
                _locationData.value = repository.fetchLocationDetails(locationKey)
                Timber.d("onFetchLocationWeatherData: Success!")
            } catch (e: Exception) {
                Timber.e(e, "onFetchLocationWeatherData: Failure")
            }
        }
    }
}

class LocationDetailsViewModelFactory(
    private val locationKey: String,
    private val repository: LocationsRepository
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationDetailsViewModel::class.java)) {
            return LocationDetailsViewModel(locationKey, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}