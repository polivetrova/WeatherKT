package com.gbhw.weatherapp.model.entities

data class FavouriteCities(
    var favouriteCitiesList: MutableList<Weather> = mutableListOf(
        Weather(
            City(
                "London",
                "Great Britain",
                51.5085300,
                -0.1257400
            ), 1, 2, true
        )
    )
) : MarkingAsFavourite {

    override fun getListOfFavourites(): MutableList<Weather> = favouriteCitiesList

    override fun removeFromListOfFavourites(weather: Weather) {
        favouriteCitiesList.remove(weather)
    }

    override fun addToListOfFavourites(element: Weather) {
        if(!favouriteCitiesList.contains(element)) {
            favouriteCitiesList.add(element)
        }
    }
}




