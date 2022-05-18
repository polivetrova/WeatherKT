package com.gbhw.weatherapp.model.entities.restEntites

import com.google.gson.annotations.SerializedName

data class FactDTO(

    val temp: Int?,
    @SerializedName("feels_like") val feelsLike: Int?,
    val condition: String?,
    )
