package com.gbhw.weatherapp.model.entities

data class FavouriteCities(
    var favouriteCitiesList: MutableList<Weather> = mutableListOf()
) : MarkingAsFavourite {

    override fun getListOfFavourites(): MutableList<Weather> = favouriteCitiesList

    override fun removeFromListOfFavourites(element: Weather) {
        favouriteCitiesList.remove(element)
    }

    override fun addToListOfFavourites(element: Weather) {
        if (!favouriteCitiesList.contains(element)) {
            favouriteCitiesList.add(element)
        }
    }
}




