package com.gbhw.weatherapp.model.repository

import com.gbhw.weatherapp.model.entities.Weather
import com.gbhw.weatherapp.model.entities.FavouriteCities
import com.gbhw.weatherapp.model.entities.getWorldCities

class RepositoryImpl : Repository {

    private val favourites = FavouriteCities()

    override fun getCitiesList(): List<Weather> = getWorldCities()
    override fun getWeatherFromServer(): Weather = Weather()
    override fun getWeatherFromLocalStorage(): Weather = Weather()
    override fun getCitiesFromFavourites(): MutableList<Weather> =
        favourites.getListOfFavourites()

    override fun addCityToFavourites(weather: Weather) = favourites.addToListOfFavourites(weather)

    override fun removeCityFromFavourites(weather: Weather) =
        favourites.removeFromListOfFavourites(weather)

}