package com.gbhw.weatherapp

import com.gbhw.weatherapp.model.repository.Repository
import com.gbhw.weatherapp.model.repository.RepositoryImpl
import com.gbhw.weatherapp.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository>{ RepositoryImpl() }

    viewModel { MainViewModel(get()) }
}