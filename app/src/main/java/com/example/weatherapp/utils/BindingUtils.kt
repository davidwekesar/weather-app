package com.example.weatherapp.utils

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.weatherapp.R
import com.example.weatherapp.network.AccuWeatherApiStatus

@BindingAdapter("app:srcCompat")
fun setWeatherImage(imageView: ImageView, weatherIcon: Int) {
    imageView.setImageResource(getWeatherImageResource(weatherIcon))
}

@BindingAdapter("textTime")
fun setTimeText(textView: TextView, epochTime: Long) {
    textView.text = convertToTime(epochTime)
}

@BindingAdapter("textDate")
fun setDateText(textView: TextView, epochTime: Long) {
    textView.text = convertToDate(epochTime)
}

@BindingAdapter("app:srcCompat")
fun setFavoriteButtonState(imageButton: ImageButton, isFavorite: Boolean) {
    if (isFavorite) {
        imageButton.setImageResource(R.drawable.ic_favorite_24)
    } else {
        imageButton.setImageResource(R.drawable.ic_favorite_border_24)
    }
}

@BindingAdapter("loadStateVisibility")
fun setLoadStateVisibility(viewGroup: ViewGroup, apiStatus: AccuWeatherApiStatus) {
    when (apiStatus) {
        AccuWeatherApiStatus.LOADING -> viewGroup.visibility = View.VISIBLE
        AccuWeatherApiStatus.DONE -> viewGroup.visibility = View.INVISIBLE
        AccuWeatherApiStatus.ERROR -> viewGroup.visibility = View.INVISIBLE
    }
}

@BindingAdapter("errorStateVisibility")
fun setErrorStateVisibility(viewGroup: ViewGroup, apiStatus: AccuWeatherApiStatus) {
    when (apiStatus) {
        AccuWeatherApiStatus.LOADING -> viewGroup.visibility = View.INVISIBLE
        AccuWeatherApiStatus.DONE -> viewGroup.visibility = View.INVISIBLE
        AccuWeatherApiStatus.ERROR -> viewGroup.visibility = View.VISIBLE
    }
}