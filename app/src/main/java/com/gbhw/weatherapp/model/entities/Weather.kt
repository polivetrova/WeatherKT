package com.gbhw.weatherapp.model.entities

data class Weather(
    val city : City = getDefaultCity(),
    val temperature : Int = 0,
    val feelsLike : Int = 0,
)

fun getDefaultCity() = City("Moscow", 55.755826, 37.617299900000035)
