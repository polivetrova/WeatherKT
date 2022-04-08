package com.gbhw.weatherapp.model.repository

import com.gbhw.weatherapp.model.entities.Weather

interface Repository {
    fun getWeatherFromServer() : Weather
    fun getWeatherFromLocalStorage() : Weather

}