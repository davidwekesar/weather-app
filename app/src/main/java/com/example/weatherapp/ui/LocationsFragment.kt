package com.example.weatherapp.ui

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentLocationsBinding
import com.example.weatherapp.domain.Location
import com.example.weatherapp.viewmodels.LocationsViewModel
import com.example.weatherapp.viewmodels.LocationsViewModelFactory
import com.example.weatherapp.ui.adapters.LocationAdapter
import com.example.weatherapp.ui.adapters.LocationListener
import com.google.android.material.appbar.AppBarLayout
import timber.log.Timber

class LocationsFragment : Fragment() {

    private var _binding: FragmentLocationsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LocationsViewModel by viewModels {
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

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        val typeface = ResourcesCompat.getFont(requireContext(), R.font.quicksand_medium)
        val searchText =
            searchView.findViewById<View>(androidx.appcompat.R.id.search_src_text) as TextView
        searchText.typeface = typeface
        searchView.queryHint = getString(R.string.location_hint)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.search(query)
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                viewModel.search(text)
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
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