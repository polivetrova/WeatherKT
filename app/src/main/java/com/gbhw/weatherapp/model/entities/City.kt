package com.gbhw.weatherapp.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    val city: String,
    val country: String,
    val lat: Double,
    val lon: Double,
) : Parcelable
