package com.gbhw.weatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gbhw.weatherapp.model.AppState
import com.gbhw.weatherapp.model.repository.Repository
import java.lang.Thread.sleep

class MainViewModel(private val repository : Repository): ViewModel() {
    private val localLiveData = MutableLiveData<AppState>()
    val liveData: LiveData<AppState> get() = localLiveData

    fun getWeather() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        localLiveData.value = AppState.Loading
        Thread{
            sleep(1000)
            localLiveData.postValue(AppState.Success(repository.getWeatherFromLocalStorage()))
        }.start()
    }
}