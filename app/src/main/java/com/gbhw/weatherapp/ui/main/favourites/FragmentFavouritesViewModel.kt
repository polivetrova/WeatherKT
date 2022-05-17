package com.gbhw.weatherapp.ui.main.favourites

import androidx.lifecycle.ViewModel
import com.gbhw.weatherapp.model.entities.Weather
import com.gbhw.weatherapp.model.repository.Repository

class FragmentFavouritesViewModel(private val repository: Repository) : ViewModel() {

    fun getCitiesList() = repository.getCitiesFromFavourites()
    fun addToFavourites(weather: Weather) = repository.addCityToFavourites(weather)
    fun removeFromFavourites(weather: Weather) = repository.removeCityFromFavourites(weather)
}