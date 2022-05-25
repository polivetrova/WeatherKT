package com.gbhw.weatherapp.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import coil.load
import com.gbhw.weatherapp.R
import com.gbhw.weatherapp.databinding.FragmentDetailsBinding
import com.gbhw.weatherapp.model.AppState
import com.gbhw.weatherapp.model.entities.Weather
import com.gbhw.weatherapp.model.entities.getDefaultCity
import com.gbhw.weatherapp.ui.main.showSnackBarWithAction
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentHome : Fragment() {

    // геолокация???

    companion object {
        fun newInstance() = FragmentHome()
    }

    private val viewModel: FragmentHomeViewModel by viewModel()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val observer = Observer<AppState> { renderData(it) }
        viewModel.liveData.observe(viewLifecycleOwner, observer)
        viewModel.getWeather(getDefaultCity().lat, getDefaultCity().lon)
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                progressBar.visibility = View.GONE
                weatherGroup.visibility = View.VISIBLE
                setData(appState.weatherData[0])

                imageView.load("https://cdn.pixabay.com/photo/2020/08/30/19/37/mosque-5530453_1280.png") {
                    crossfade(true)
                }
            }
            is AppState.Loading -> {
                weatherGroup.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                progressBar.visibility = View.GONE
                weatherGroup.visibility = View.INVISIBLE

                view?.showSnackBarWithAction(
                    "Error",
                    "Reload",
                    { viewModel.getWeather(getDefaultCity().lat, getDefaultCity().lon) })
            }
        }
    }

    private fun setData(weatherData: Weather) = with(binding) {
        cityName.text = weatherData.city.city
        cityCoordinates.text = String.format(
            getString(R.string.city_coordinates),
            weatherData.city.lat.toString(),
            weatherData.city.lon.toString()
        )
        countryName.text = weatherData.city.country
        weatherCondition.text = weatherData.condition
        temperatureValue.text = weatherData.temperature.toString()
        feelsLikeValue.text = weatherData.feelsLike.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}