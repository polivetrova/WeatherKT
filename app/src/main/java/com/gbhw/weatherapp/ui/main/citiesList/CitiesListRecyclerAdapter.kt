package com.gbhw.weatherapp.ui.main.citiesList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gbhw.weatherapp.R
import com.gbhw.weatherapp.model.entities.City
import com.google.android.material.textview.MaterialTextView

class CitiesListRecyclerAdapter(private val citiesData: List<City>) :
    RecyclerView.Adapter<CitiesListRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CitiesListRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CitiesListRecyclerAdapter.ViewHolder, position: Int) {
        val city: City = citiesData[position]
        holder.bind(city)
    }

    override fun getItemCount(): Int {
        return citiesData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityName: MaterialTextView = itemView.findViewById(R.id.city_name)
        private val countryName: MaterialTextView = itemView.findViewById(R.id.country_name)

        fun bind(city: City) {
            cityName.text = city.city
            countryName.text = city.country
        }
    }
}