package com.gbhw.weatherapp.ui.main.citiesList

import androidx.lifecycle.ViewModel
import com.gbhw.weatherapp.model.repository.Repository

class FragmentCitiesListViewModel(private val repository: Repository) : ViewModel() {

    // брать список городов (апи/бд)

    fun getCitiesList() = repository.getCitiesList()
}