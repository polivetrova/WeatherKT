package com.gbhw.weatherapp

import com.gbhw.weatherapp.model.repository.Repository
import com.gbhw.weatherapp.model.repository.RepositoryImpl
import com.gbhw.weatherapp.ui.main.citiesList.FragmentCitiesListViewModel
import com.gbhw.weatherapp.ui.main.favourites.FragmentFavouritesViewModel
import com.gbhw.weatherapp.ui.main.home.FragmentHomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }

    viewModel { FragmentHomeViewModel(get()) }
    viewModel { FragmentFavouritesViewModel(get()) }
    viewModel { FragmentCitiesListViewModel(get()) }
}