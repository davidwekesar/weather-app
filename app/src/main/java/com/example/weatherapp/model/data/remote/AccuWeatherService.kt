package com.example.weatherapp.model.data.remote

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.model.data.models.LocationData
import com.example.weatherapp.model.data.models.LocationListItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://dataservice.accuweather.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface AccuWeatherService {
    @GET("currentconditions/v1/topcities/50?apikey=${BuildConfig.apiKey}")
    suspend fun fetchCitiesWeatherList(): List<LocationListItem>

    @GET("currentconditions/v1/{locationKey}?apikey=${BuildConfig.apiKey}&details=true")
    suspend fun fetchLocationWeatherData(@Path("locationKey") locationKey: String): List<LocationData>
}

object AccuWeather {
    val accuWeatherService: AccuWeatherService by lazy {
        retrofit.create(AccuWeatherService::class.java)
    }
}