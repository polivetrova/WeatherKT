package com.gbhw.weatherapp.model.entities

interface MarkingAsFavourite {
    fun getListOfFavourites(): MutableList<Weather>
    fun removeFromListOfFavourites(element: Weather)
    fun addToListOfFavourites(element: Weather)
}
