package com.gbhw.weatherapp.ui.main.citiesList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gbhw.weatherapp.R
import com.gbhw.weatherapp.databinding.CityItemBinding
import com.gbhw.weatherapp.model.entities.Weather
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class CitiesListRecyclerAdapter(private val itemClickListener: FragmentCitiesList.OnItemViewClickListener) :
    RecyclerView.Adapter<CitiesListRecyclerAdapter.ViewHolder>() {

    private lateinit var binding: CityItemBinding
    private var weatherData: List<Weather> = listOf()

    fun setWeather(data: List<Weather>) {
        weatherData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CitiesListRecyclerAdapter.ViewHolder {
        binding = CityItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CitiesListRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(weatherData[position])
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(weather: Weather) = with(binding) {

            cityName.text = weather.city.city
            countryName.text = weather.city.country
//            itemTemperature.text = weather.temperature.toString()
            if (weather.isFavourite) {
                markAsFavourite.setImageResource(R.drawable.ic_star)
            }

            root.setOnClickListener {
                itemClickListener.onItemViewClick(weather)
            }

            markAsFavourite.setOnClickListener {
                if (weather.isFavourite) {
                    weather.isFavourite = false
                    markAsFavourite.setImageResource(R.drawable.ic_star_outline)
                } else {
                    weather.isFavourite = true
                    markAsFavourite.setImageResource(R.drawable.ic_star)
                }
                itemClickListener.onFavouriteButtonClick(weather)
            }
        }
    }
}
