package com.example.weatherapp.viewmodels

import androidx.lifecycle.*
import com.example.weatherapp.domain.LocationDetails
import com.example.weatherapp.network.AccuWeatherApiStatus
import com.example.weatherapp.repository.LocationsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import timber.log.Timber

class LocationDetailsViewModel @AssistedInject constructor(
    private val repository: LocationsRepository,
    @Assisted private val locationKey: String,
) : ViewModel() {

    private val _apiStatus = MutableLiveData<AccuWeatherApiStatus>()
    val apiStatus: LiveData<AccuWeatherApiStatus> get() = _apiStatus

    private val _locationData = MutableLiveData<List<LocationDetails>>()
    val locationDetails: LiveData<List<LocationDetails>> get() = _locationData

    init {
        fetchLocationDetails(locationKey)
    }

    private fun fetchLocationDetails(locationKey: String) {
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

    companion object {

        fun providesFactory(
            assistedFactory: LocationDetailsViewModelFactory,
            locationKey: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST", "WRONG_NULLABILITY_FOR_JAVA_OVERRIDE")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(locationKey) as T
            }
        }
    }
}

@AssistedFactory
interface LocationDetailsViewModelFactory {
    fun create(locationKey: String): LocationDetailsViewModel
}