package com.gbhw.weatherapp.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gbhw.weatherapp.model.AppState
import com.gbhw.weatherapp.model.repository.Repository
import java.lang.Thread.sleep

class FragmentHomeViewModel(private val repository: Repository) : ViewModel() {
    private val localLiveData = MutableLiveData<AppState>()
    val liveData: LiveData<AppState> get() = localLiveData

    fun getWeather(lat: Double, lon: Double) = getDataFromLocalSource(lat, lon)

    private fun getDataFromLocalSource(lat: Double, lon: Double) {
        localLiveData.value = AppState.Loading
        Thread {
            val data = repository.getWeatherFromServer(lat, lon)
            localLiveData.postValue(AppState.Success(listOf(data)))
        }.start()


//        localLiveData.value = AppState.Loading
//        Thread {
//            sleep(1000)
//            localLiveData.postValue(AppState.Success(repository.getWeatherFromLocalStorage()))
//        }.start()
    }
}