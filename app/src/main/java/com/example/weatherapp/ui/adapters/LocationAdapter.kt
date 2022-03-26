package com.example.weatherapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ListItemLocationBinding
import com.example.weatherapp.domain.Location
import com.example.weatherapp.utils.concatLocationName
import com.example.weatherapp.utils.convertToDate
import com.example.weatherapp.utils.convertToTime
import com.example.weatherapp.utils.formatTempString
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
        holder.bind(location, clickListener)
        holder.favoriteButton.setOnClickListener {
            with(location) {
                isFavorite = if (!isFavorite) {
                    holder.favoriteButton.setImageResource(R.drawable.ic_favorite_24)
                    true
                } else {
                    holder.favoriteButton.setImageResource(R.drawable.ic_favorite_border_24)
                    false
                }
                viewModel.updateLocation(
                    isFavorite = isFavorite,
                    locationKey = locationKey
                )
            }
        }

        if (location.isFavorite) {
            holder.favoriteButton.setImageResource(R.drawable.ic_favorite_24)
        } else {
            holder.favoriteButton.setImageResource(R.drawable.ic_favorite_border_24)
        }
    }

    override fun getItemCount(): Int = locations.size
}

class LocationViewHolder(binding: ListItemLocationBinding) : RecyclerView.ViewHolder(binding.root) {

    private val cardView: CardView = binding.cardView
    private val locationTV: TextView = binding.location
    private val dateTV: TextView = binding.date
    private val timeTV: TextView = binding.time
    private val temperatureTV: TextView = binding.temperature
    private val isFavoriteTV: TextView = binding.isFavorite
    val favoriteButton: ImageButton = binding.favoriteButton

    fun bind(location: Location, clickListener: LocationListener) {
        with(location) {
            cardView.setOnClickListener {
                clickListener.onClick(
                    locationKey,
                    concatLocationName(city, country)
                )
            }
            locationTV.text = concatLocationName(city, country)
            temperatureTV.text = formatTempString(temperature)
            dateTV.text = convertToDate(epochTime)
            timeTV.text = convertToTime(epochTime)
            isFavoriteTV.text = isFavorite.toString()
        }
    }
}

class LocationListener(val clickListener: (locationKey: String, location: String) -> Unit) {
    fun onClick(locationKey: String, location: String) = clickListener(locationKey, location)
}