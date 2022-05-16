package com.gbhw.weatherapp.model.repository

import com.gbhw.weatherapp.model.entities.City
import com.gbhw.weatherapp.model.entities.Weather

interface Repository {
    fun getCitiesList(): List<Weather>
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage(): Weather

}