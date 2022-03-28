package com.example.weatherapp.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentLocationsBinding
import com.example.weatherapp.domain.Location
import com.example.weatherapp.viewmodels.LocationsViewModel
import com.example.weatherapp.viewmodels.LocationsViewModelFactory
import com.example.weatherapp.ui.adapters.LocationAdapter
import com.example.weatherapp.ui.adapters.LocationListener
import timber.log.Timber

class LocationsFragment : Fragment() {

    private var _binding: FragmentLocationsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LocationsViewModel by activityViewModels() {
        LocationsViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationsBinding.inflate(inflater, container, false)

        viewModel.locations.observe(viewLifecycleOwner) { locations: List<Location> ->
            binding.progressBar.visibility = View.INVISIBLE
            val adapter =
                LocationAdapter(locations, viewModel, LocationListener { locationKey, location ->
                    navigateToLocationDetailsFragment(locationKey, location)
                })
            binding.citiesRecyclerView.adapter = adapter
        }

        return binding.root
    }

    private fun navigateToLocationDetailsFragment(locationKey: String, location: String) {
        val action = LocationsFragmentDirections
            .actionCitiesListFragmentToLocationDetailsFragment(locationKey, location)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}