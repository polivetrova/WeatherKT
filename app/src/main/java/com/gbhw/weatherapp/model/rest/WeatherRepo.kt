package com.gbhw.weatherapp.model.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRepo {
    val api: WeatherAPI by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(APIUtils.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(APIUtils.getOkHTTPBuilderWithHeaders())
            .build()

        adapter.create(WeatherAPI::class.java)
    }
}