package com.gbhw.weatherapp.model.repository

import com.gbhw.weatherapp.model.entities.Weather
import com.gbhw.weatherapp.model.entities.FavouriteCities
import com.gbhw.weatherapp.model.entities.getWorldCities
import com.gbhw.weatherapp.model.rest.WeatherRepo

class RepositoryImpl : Repository {

    private val favourites = FavouriteCities()

    override fun getWeatherFromServer(lat: Double, lon: Double): Weather {
        val dto = WeatherRepo.api.getWeather(lat, lon).execute().body()
        return Weather(
            temperature = dto?.fact?.temp ?: 0,
            feelsLike = dto?.fact?.feelsLike ?: 0,
            condition = dto?.fact?.condition
        )
    }

    override fun getWeatherFromLocalStorage(): List<Weather> = listOf(Weather())

    override fun getCitiesList(): List<Weather> = getWorldCities()
    override fun getCitiesFromFavourites(): MutableList<Weather> =
        favourites.getListOfFavourites()

    override fun addCityToFavourites(weather: Weather) = favourites.addToListOfFavourites(weather)

    override fun removeCityFromFavourites(weather: Weather) =
        favourites.removeFromListOfFavourites(weather)
}