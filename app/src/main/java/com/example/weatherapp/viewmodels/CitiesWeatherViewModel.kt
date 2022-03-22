package com.example.weatherapp.viewmodels

import androidx.lifecycle.*
import com.example.weatherapp.model.data.models.LocationListItem
import com.example.weatherapp.model.repositories.CitiesWeatherRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class CitiesWeatherViewModel(
    private val repository: CitiesWeatherRepository
) : ViewModel() {
    private val _citiesWeatherList = MutableLiveData<List<LocationListItem>>()
    val citiesWeatherList: LiveData<List<LocationListItem>> get() = _citiesWeatherList

    init {
        fetchCitiesWeatherList()
    }

    private fun fetchCitiesWeatherList() {
        viewModelScope.launch {
            try {
                _citiesWeatherList.value = repository.fetchCitiesWeatherList()
                Timber.d("fetchCitiesWeatherList:success")
            } catch (e: Exception) {
                Timber.e(e, "fetchCitiesWeatherList:failure")
            }
        }
    }
}

class CitiesWeatherViewModelFactory(
    private val repository: CitiesWeatherRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CitiesWeatherViewModel::class.java)) {
            return CitiesWeatherViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}