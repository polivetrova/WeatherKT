package com.gbhw.weatherapp.model.rest

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object APIUtils {

    private val baseURLMainPart = "https://api.weather.yandex.ru/"
    private val baseURLVersion = "v2/"
    val baseURL = "$baseURLMainPart$baseURLVersion"

    fun getOkHTTPBuilderWithHeaders(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(10, TimeUnit.SECONDS)
        httpClient.readTimeout(10, TimeUnit.SECONDS)
        httpClient.writeTimeout(10, TimeUnit.SECONDS)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("X-Yandex-API-Key", "adbdb5d7-8e0f-4433-af7e-b5d9f6c61a75")
                .method(original.method, original.body)
                .build()

            chain.proceed(request)
        }

        return httpClient.build()
    }
}