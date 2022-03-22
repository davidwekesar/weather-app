package com.example.weatherapp.viewmodels

import androidx.lifecycle.*
import com.example.weatherapp.model.data.models.LocationData
import com.example.weatherapp.model.repositories.CitiesWeatherRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class LocationDetailsViewModel(
    private val locationKey: String,
    private val repository: CitiesWeatherRepository
) : ViewModel() {

    private val _locationData = MutableLiveData<List<LocationData>>()
    val locationData: LiveData<List<LocationData>> get() = _locationData

    init {
        fetchLocationWeatherData()
    }

    private fun fetchLocationWeatherData() {
        viewModelScope.launch {
            try {
                _locationData.value = repository.fetchLocationWeatherData(locationKey)
                Timber.d("onFetchLocationWeatherData: Success!")
            } catch (e: Exception) {
                Timber.e(e, "onFetchLocationWeatherData: Failure")
            }
        }
    }
}

class LocationDetailsViewModelFactory(
    private val locationKey: String,
    private val repository: CitiesWeatherRepository
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationDetailsViewModel::class.java)) {
            return LocationDetailsViewModel(locationKey, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}