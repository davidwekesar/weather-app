package com.example.weatherapp.views.citieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ListItemCityBinding
import com.example.weatherapp.model.data.models.CityWeather

class CitiesListAdapter(
    private val citiesWeatherList: List<CityWeather>
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
        holder.bind(cityWeather)
    }

    override fun getItemCount(): Int = citiesWeatherList.size
}

class CityViewHolder(binding: ListItemCityBinding) : RecyclerView.ViewHolder(binding.root) {

    private val cityTV: TextView = binding.city
    private val temperatureTV: TextView = binding.temperature

    fun bind(cityWeather: CityWeather) {
        cityTV.text = cityWeather.city
        temperatureTV.text = cityWeather.temperature.metric.value.toString()
    }
}