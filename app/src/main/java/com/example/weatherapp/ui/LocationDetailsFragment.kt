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
import com.example.weatherapp.databinding.FragmentLocationDetailsBinding
import com.example.weatherapp.viewmodels.LocationDetailsViewModel
import com.example.weatherapp.viewmodels.LocationDetailsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LocationDetailsFragment : Fragment() {

    private var _binding: FragmentLocationDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: LocationDetailsFragmentArgs by navArgs()
    @Inject lateinit var locationDetailsViewModelFactory: LocationDetailsViewModelFactory
    private val viewModel: LocationDetailsViewModel by viewModels {
        LocationDetailsViewModel.providesFactory(
            assistedFactory = locationDetailsViewModelFactory,
            locationKey = args.locationKey
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationDetailsBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this

        with(args) {
            binding.city = city
            binding.country = country
        }

        binding.viewModel = viewModel

        viewModel.locationDetails.observe(viewLifecycleOwner) { locationDetailsList ->
            binding.loadingState.visibility = View.INVISIBLE
            val locationDetails = locationDetailsList[0]
            binding.locationDetails = locationDetails
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val menuItem = menu.findItem(R.id.search)
        if (menuItem != null) menuItem.isVisible = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}