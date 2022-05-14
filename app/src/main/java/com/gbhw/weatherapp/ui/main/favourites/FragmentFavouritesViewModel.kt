package com.gbhw.weatherapp.ui.main.favourites

import androidx.lifecycle.ViewModel
import com.gbhw.weatherapp.model.repository.Repository

class FragmentFavouritesViewModel(private val repository: Repository) : ViewModel() {

    // сделать список избранных городов, добавление новых, удаление старых
    // отображение температуры в списке избранных городов
    // добавить onClickListener на itemView

    val adapter = FavouritesRecyclerAdapter(repository.getCitiesList())

//    fun getCitiesList() = repository.getCitiesList()
}