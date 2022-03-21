package com.example.weatherapp.views.citieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ListItemCityBinding

class CitiesListAdapter : RecyclerView.Adapter<CityViewHolder>() {
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
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

class CityViewHolder(binding: ListItemCityBinding) : RecyclerView.ViewHolder(binding.root) {

    private val cityTV: TextView = binding.city
    private val temperatureTV: TextView = binding.temperature

    fun bind() {

    }
}