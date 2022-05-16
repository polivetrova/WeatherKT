package com.gbhw.weatherapp.ui.main.favourites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gbhw.weatherapp.databinding.CityItemBinding
import com.gbhw.weatherapp.model.entities.Weather

class FavouritesRecyclerAdapter(private val itemClickListener: FragmentFavourites.OnItemViewClickListener) :
    RecyclerView.Adapter<FavouritesRecyclerAdapter.ViewHolder>() {

    private lateinit var binding: CityItemBinding
    private var weatherData: List<Weather> = listOf()

    fun setWeather(data: List<Weather>) {
        weatherData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouritesRecyclerAdapter.ViewHolder {
        binding = CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(weatherData[position])
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weather: Weather) = with(binding) {
            cityName.text = weather.city.city
            countryName.text = weather.city.country
            root.setOnClickListener { itemClickListener.onItemViewClick(weather) }
        }
    }
}