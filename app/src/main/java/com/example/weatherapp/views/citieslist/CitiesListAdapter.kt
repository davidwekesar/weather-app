package com.example.weatherapp.views.citieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ListItemCityBinding
import com.example.weatherapp.model.data.models.LocationListItem
import com.example.weatherapp.utils.concatLocationName
import com.example.weatherapp.utils.convertToDate
import com.example.weatherapp.utils.convertToTime
import com.example.weatherapp.utils.formatTempString

class CitiesListAdapter(
    private val citiesWeatherList: List<LocationListItem>,
    private val clickListener: LocationListener
) : RecyclerView.Adapter<CityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItemCityBinding = ListItemCityBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return CityViewHolder(listItemCityBinding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val cityWeather = citiesWeatherList[position]
        holder.bind(cityWeather, clickListener)
    }

    override fun getItemCount(): Int = citiesWeatherList.size
}

class CityViewHolder(binding: ListItemCityBinding) : RecyclerView.ViewHolder(binding.root) {

    private val cardView: CardView = binding.cardView
    private val locationTV: TextView = binding.location
    private val dateTV: TextView = binding.date
    private val timeTV: TextView = binding.time
    private val temperatureTV: TextView = binding.temperature

    fun bind(locationListItem: LocationListItem, clickListener: LocationListener) {
        with(locationListItem) {
            cardView.setOnClickListener { clickListener.onClick(locationKey) }
            locationTV.text = concatLocationName(city, country.name)
            temperatureTV.text = formatTempString(temperature.metric.value)
            dateTV.text = convertToDate(epochTime)
            timeTV.text = convertToTime(epochTime)
        }
    }
}

class LocationListener(val clickListener: (locationKey: String) -> Unit) {
    fun onClick(locationKey: String) = clickListener(locationKey)
}