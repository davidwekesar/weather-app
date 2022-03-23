package com.example.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.database.getDatabase
import com.example.weatherapp.databinding.FragmentLocationDetailsBinding
import com.example.weatherapp.network.datatransferobjects.NetworkLocationDetails
import com.example.weatherapp.repository.LocationsRepository
import com.example.weatherapp.utils.convertToDate
import com.example.weatherapp.utils.convertToTime
import com.example.weatherapp.utils.formatTempString
import com.example.weatherapp.viewmodels.LocationDetailsViewModel
import com.example.weatherapp.viewmodels.LocationDetailsViewModelFactory

class LocationDetailsFragment : Fragment() {

    private var _binding: FragmentLocationDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: LocationDetailsFragmentArgs by navArgs()
    private val repository: LocationsRepository by lazy {
        LocationsRepository(getDatabase(requireActivity().application))
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

        viewModel.networkLocationDetails.observe(viewLifecycleOwner) { locationDataList ->
            val locationData = locationDataList[0]
            updateUI(locationData)
        }

        return binding.root
    }

    private fun updateUI(networkLocationDetails: NetworkLocationDetails) {
        binding.location.text = args.location
        binding.date.text = convertToDate(networkLocationDetails.epochTime)
        binding.time.text = convertToTime(networkLocationDetails.epochTime)
        binding.weather.text = networkLocationDetails.weatherText
        binding.temperature.text = formatTempString(networkLocationDetails.temperature.metric.value.toInt())
        binding.windSpeed.text = formatWindSpeedString(networkLocationDetails.wind.speed.metric.value)
        binding.humidity.text = formatHumidityString(networkLocationDetails.humidity)
        binding.visibility.text = formatVisibilityString(networkLocationDetails.visibility.metric.value)
        binding.pressure.text = formatPressureString(networkLocationDetails.pressure.metric.value)
        binding.dewPoint.text = formatTempString(networkLocationDetails.dewPoint.metric.value.toInt())
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