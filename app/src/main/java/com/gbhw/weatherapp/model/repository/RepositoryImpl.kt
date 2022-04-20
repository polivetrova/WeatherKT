package com.gbhw.weatherapp.model.repository

import com.gbhw.weatherapp.model.entities.City
import com.gbhw.weatherapp.model.entities.Weather
import com.gbhw.weatherapp.model.entities.getDefaultCity

class RepositoryImpl : Repository {

    override fun getCitiesList(): List<City> {
        return listOf(
            getDefaultCity(),
            City("Seoul", "South Korea", 37.532600, 127.024612),
            City("Sao Paulo", "Brazil", -23.533773, -46.625290),
            City("Seoul", "South Korea", 37.532600, 127.024612),
            City("Sao Paulo", "Brazil", -23.533773, -46.625290),
            City("Seoul", "South Korea", 37.532600, 127.024612),
            City("Sao Paulo", "Brazil", -23.533773, -46.625290),
            City("Seoul", "South Korea", 37.532600, 127.024612),
            City("Sao Paulo", "Brazil", -23.533773, -46.625290),
            City("Seoul", "South Korea", 37.532600, 127.024612),
            City("Sao Paulo", "Brazil", -23.533773, -46.625290),
            City("Seoul", "South Korea", 37.532600, 127.024612),
            City("Sao Paulo", "Brazil", -23.533773, -46.625290),
            City("Seoul", "South Korea", 37.532600, 127.024612),
            City("Sao Paulo", "Brazil", -23.533773, -46.625290),
            City("Seoul", "South Korea", 37.532600, 127.024612),
            City("Sao Paulo", "Brazil", -23.533773, -46.625290),
            City("Seoul", "South Korea", 37.532600, 127.024612),
            City("Sao Paulo", "Brazil", -23.533773, -46.625290)
        )
    }

    override fun getWeatherFromServer(): Weather = Weather()

    override fun getWeatherFromLocalStorage(): Weather = Weather()
}