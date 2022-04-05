package com.example.weatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ListItemLocationBinding
import com.example.weatherapp.domain.Location
import com.example.weatherapp.viewmodels.LocationsViewModel

class LocationAdapter(
    private val locations: List<Location>,
    private val viewModel: LocationsViewModel,
    private val clickListener: LocationListener
) : RecyclerView.Adapter<LocationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItemCityBinding = ListItemLocationBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return LocationViewHolder(listItemCityBinding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = locations[position]
        holder.bind(location, viewModel, clickListener)
    }

    override fun getItemCount(): Int = locations.size
}

class LocationViewHolder(private val binding: ListItemLocationBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(location: Location, viewModel: LocationsViewModel, clickListener: LocationListener) {
        binding.location = location
        binding.locationListener = clickListener
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}

class LocationListener(val clickListener: (locationKey: String, location: String) -> Unit) {
    fun onClick(locationKey: String, location: String) = clickListener(locationKey, location)
}