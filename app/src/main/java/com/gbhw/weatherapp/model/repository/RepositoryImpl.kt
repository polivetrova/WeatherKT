package com.gbhw.weatherapp.model.repository

import com.gbhw.weatherapp.model.entities.Weather
import com.gbhw.weatherapp.model.entities.getWorldCities

class RepositoryImpl : Repository {

    override fun getCitiesList(): List<Weather> = getWorldCities()
    override fun getWeatherFromServer(): Weather = Weather()
    override fun getWeatherFromLocalStorage(): Weather = Weather()
}