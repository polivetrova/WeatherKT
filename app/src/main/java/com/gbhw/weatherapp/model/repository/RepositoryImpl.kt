package com.gbhw.weatherapp.model.repository

import com.gbhw.weatherapp.model.entities.Weather

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(): Weather = Weather()

    override fun getWeatherFromLocalStorage(): Weather = Weather()
}