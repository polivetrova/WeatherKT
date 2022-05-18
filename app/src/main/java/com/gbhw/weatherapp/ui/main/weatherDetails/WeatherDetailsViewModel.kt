package com.gbhw.weatherapp.ui.main.weatherDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gbhw.weatherapp.model.AppState
import com.gbhw.weatherapp.model.repository.Repository

class WeatherDetailsViewModel(private val repository: Repository) : ViewModel(){
    private val localLiveData : MutableLiveData<AppState> = MutableLiveData()
    val weatherLiveData : LiveData<AppState> get() = localLiveData

    fun loadData(lat: Double, lon: Double) {
        localLiveData.value = AppState.Loading
        Thread {
            val data = repository.getWeatherFromServer(lat, lon)
            localLiveData.postValue(AppState.Success(listOf(data)))
        }.start()
    }

}