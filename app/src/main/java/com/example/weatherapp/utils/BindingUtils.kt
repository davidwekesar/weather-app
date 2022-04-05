package com.example.weatherapp.utils

import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.weatherapp.R
import com.example.weatherapp.domain.Location

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