package com.gbhw.weatherapp.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = 0,
    val condition: String? = "N/A",
    var isFavourite: Boolean = false,
) : Parcelable

fun getDefaultCity() = City("Moscow", "Russia", 55.755826, 37.617299900000035)

fun getWorldCities() = listOf(
    Weather(City("Moscow", "Russia", 55.755826, 37.617299900000035), 1, 2),
    Weather(City("Saint Petersburg", "Russia", 59.9342802, 30.335098600000038), 3, 3),
    Weather(City("Novosibirsk", "Russia", 55.00835259999999, 82.93573270000002), 5, 6),
    Weather(City("London", "Great Britain", 51.5085300, -0.1257400), 1, 2),
    Weather(City("Tokyo", "Japan", 35.6895000, 139.6917100), 3, 4),
    Weather(City("Paris", "France", 48.8534100, 2.3488000), 5, 6),
    Weather(City("Berlin", "Germany", 52.52000659999999, 13.404953999999975), 7, 8),
    Weather(City("Rome", "Italy", 41.9027835, 12.496365500000024), 9, 10),
    Weather(City("Omsk", "Russia", 54.9884804, 73.32423610000001), 15, 16),
    Weather(City("Rostov-on-Don", "Russia", 47.2357137, 39.701505), 17, 18),
    Weather(City("Ufa", "Russia", 54.7387621, 55.972055400000045), 19, 20)
)
