package com.example.weatherapp.viewmodels

import androidx.lifecycle.*
import com.example.weatherapp.domain.LocationDetails
import com.example.weatherapp.network.AccuWeatherApiStatus
import com.example.weatherapp.repository.LocationsRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class LocationDetailsViewModel(
    private val locationKey: String,
    private val repository: LocationsRepository
) : ViewModel() {

    private val _apiStatus = MutableLiveData<AccuWeatherApiStatus>()
    val apiStatus: LiveData<AccuWeatherApiStatus> get() = _apiStatus

    private val _locationData = MutableLiveData<List<LocationDetails>>()
    val locationDetails: LiveData<List<LocationDetails>> get() = _locationData

    init {
        fetchLocationDetails()
    }

    private fun fetchLocationDetails() {
        viewModelScope.launch {
            try {
                _apiStatus.value = AccuWeatherApiStatus.LOADING
                _locationData.value = repository.fetchLocationDetails(locationKey)
                _apiStatus.value = AccuWeatherApiStatus.DONE
                Timber.d("onFetchLocationWeatherData: Success!")
            } catch (e: Exception) {
                _apiStatus.value = AccuWeatherApiStatus.ERROR
                Timber.e(e, "onFetchLocationWeatherData: Failure")
            }
        }
    }
}

class LocationDetailsViewModelFactory(
    private val locationKey: String,
    private val repository: LocationsRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationDetailsViewModel::class.java)) {
            return LocationDetailsViewModel(locationKey, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}