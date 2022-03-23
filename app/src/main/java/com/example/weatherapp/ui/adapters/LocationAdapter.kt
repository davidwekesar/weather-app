package com.example.weatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ListItemLocationBinding
import com.example.weatherapp.domain.Location
import com.example.weatherapp.utils.concatLocationName
import com.example.weatherapp.utils.convertToDate
import com.example.weatherapp.utils.convertToTime
import com.example.weatherapp.utils.formatTempString

class LocationAdapter(
    private val locations: List<Location>,
    private val clickListener: LocationListener
) : RecyclerView.Adapter<CityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItemCityBinding = ListItemLocationBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return CityViewHolder(listItemCityBinding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val cityWeather = locations[position]
        holder.bind(cityWeather, clickListener)
    }

    override fun getItemCount(): Int = locations.size
}

class CityViewHolder(binding: ListItemLocationBinding) : RecyclerView.ViewHolder(binding.root) {

    private val cardView: CardView = binding.cardView
    private val locationTV: TextView = binding.location
    private val dateTV: TextView = binding.date
    private val timeTV: TextView = binding.time
    private val temperatureTV: TextView = binding.temperature
    private val isFavoriteTV: TextView = binding.isFavorite

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