package com.example.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.R
import com.example.weatherapp.database.getDatabase
import com.example.weatherapp.databinding.FragmentLocationDetailsBinding
import com.example.weatherapp.network.datatransferobjects.NetworkLocationDetails
import com.example.weatherapp.repository.LocationsRepository
import com.example.weatherapp.utils.convertToDate
import com.example.weatherapp.utils.convertToTime
import com.example.weatherapp.utils.formatTempString
import com.example.weatherapp.utils.getIconResource
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
            binding.loadingState.visibility = View.INVISIBLE
            val locationData = locationDataList[0]
            updateUI(locationData)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val menuItem = menu.findItem(R.id.search)
        if (menuItem != null) menuItem.isVisible = false
    }

    private fun updateUI(location: NetworkLocationDetails) {
        binding.location.text = args.location
        location.weatherIcon?.let {
            binding.weatherIcon.setImageResource(getIconResource(it))
        }
        binding.date.text = convertToDate(location.epochTime)
        binding.time.text = convertToTime(location.epochTime)
        binding.weather.text = location.weatherText
        binding.temperature.text = formatTempString(location.temperature.metric.value.toInt())
        binding.windSpeed.text = formatWindSpeedString(location.wind.speed.metric.value)
        binding.humidity.text = formatHumidityString(location.humidity)
        binding.visibility.text = formatVisibilityString(location.visibility.metric.value)
        binding.pressure.text = formatPressureString(location.pressure.metric.value)
        binding.dewPoint.text = formatTempString(location.dewPoint.metric.value.toInt())
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