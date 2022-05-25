package com.gbhw.weatherapp.model.rest

import com.gbhw.weatherapp.model.entities.restEntities.WeatherDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("forecast")
    fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): Call<WeatherDTO>
}