package com.example.weatherapp.views.citieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.databinding.FragmentCitiesListBinding
import com.example.weatherapp.model.repositories.CitiesWeatherRepository
import com.example.weatherapp.viewmodels.CitiesWeatherViewModel
import com.example.weatherapp.viewmodels.CitiesWeatherViewModelFactory

class CitiesListFragment : Fragment() {

    private var _binding: FragmentCitiesListBinding? = null
    private val binding get() = _binding!!
    private val repository: CitiesWeatherRepository by lazy {
        CitiesWeatherRepository()
    }
    private val viewModel: CitiesWeatherViewModel by viewModels {
        CitiesWeatherViewModelFactory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCitiesListBinding.inflate(inflater, container, false)

        viewModel.citiesWeatherList.observe(viewLifecycleOwner) { citiesWeatherList ->
            val adapter = CitiesListAdapter(citiesWeatherList)
            binding.citiesRecyclerView.adapter = adapter
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}