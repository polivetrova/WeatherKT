package com.gbhw.weatherapp.model.repository

import com.gbhw.weatherapp.model.entities.Weather

interface Repository {
    fun getCitiesList(): List<Weather>
    fun getWeatherFromServer(lat : Double, lon : Double): Weather
    fun getWeatherFromLocalStorage(): List<Weather>
    fun getCitiesFromFavourites(): MutableList<Weather>
    fun addCityToFavourites(weather: Weather)
    fun removeCityFromFavourites(weather: Weather)
}