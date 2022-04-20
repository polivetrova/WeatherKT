package com.gbhw.weatherapp.ui.main.citiesList

import androidx.lifecycle.ViewModel
import com.gbhw.weatherapp.model.repository.Repository
import com.gbhw.weatherapp.ui.main.favourites.FavouritesRecyclerAdapter

class FragmentCitiesListViewModel(private val repository: Repository) : ViewModel() {

    // брать список городов (апи/бд)
    // добавление в избранное из списка городов

    val adapter = FavouritesRecyclerAdapter(repository.getCitiesList())

//    fun getCitiesList() = repository.getCitiesList()
}