package com.example.weatherapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.databinding.FragmentLocationDetailsBinding
import com.example.weatherapp.model.data.models.LocationData
import com.example.weatherapp.model.repositories.CitiesWeatherRepository
import com.example.weatherapp.utils.convertToDate
import com.example.weatherapp.utils.convertToTime
import com.example.weatherapp.utils.formatTempString
import com.example.weatherapp.viewmodels.LocationDetailsViewModel
import com.example.weatherapp.viewmodels.LocationDetailsViewModelFactory

class LocationDetailsFragment : Fragment() {

    private var _binding: FragmentLocationDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: LocationDetailsFragmentArgs by navArgs()
    private val repository: CitiesWeatherRepository by lazy {
        CitiesWeatherRepository()
    }
    private val viewModel: LocationDetailsViewModel by viewModels {
        LocationDetailsViewModelFactory(args.locationKey, repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationDetailsBinding.inflate(inflater, container, false)

        viewModel.locationData.observe(viewLifecycleOwner) { locationDataList ->
            val locationData = locationDataList[0]
            updateUI(locationData)
        }

        return binding.root
    }

    private fun updateUI(locationData: LocationData) {
        binding.location.text = args.location
        binding.date.text = convertToDate(locationData.epochTime)
        binding.time.text = convertToTime(locationData.epochTime)
        binding.weather.text = locationData.weatherText
        binding.temperature.text = formatTempString(locationData.temperature.metric.value)
        binding.windSpeed.text = formatWindSpeedString(locationData.wind.speed.metric.value)
        binding.humidity.text = formatHumidityString(locationData.humidity)
        binding.visibility.text = formatVisibilityString(locationData.visibility.metric.value)
        binding.pressure.text = formatPressureString(locationData.pressure.metric.value)
        binding.dewPoint.text = formatTempString(locationData.dewPoint.metric.value)
    }

    private fun formatWindSpeedString(windSpeed: Double): String {
        return "${windSpeed.toInt()} km/h"
    }

    private fun formatHumidityString(humidity: Int): String {
        return "$humidity%"
    }

    private fun formatVisibilityString(visibility: Double): String {
        return "${visibility.toInt()} km"
    }

    private fun formatPressureString(pressure: Double): String {
        return "${pressure.toInt()} mb"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}