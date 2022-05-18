package com.gbhw.weatherapp.ui.main.weatherDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.gbhw.weatherapp.R
import com.gbhw.weatherapp.databinding.FragmentDetailsBinding
import com.gbhw.weatherapp.model.AppState
import com.gbhw.weatherapp.model.entities.Weather
import com.gbhw.weatherapp.ui.main.favourites.FragmentFavouritesViewModel
import com.gbhw.weatherapp.ui.main.showSnackBarWithAction
import com.gbhw.weatherapp.ui.main.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class WeatherDetails : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WeatherDetailsViewModel by viewModel()
    private val favouritesViewModel by sharedViewModel<FragmentFavouritesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Weather>(BUNDLE_EXTRA)?.let { weather ->

            with(binding) {
                weatherGroup.visibility = View.VISIBLE

                backArrow.visibility = View.VISIBLE
                backArrow.setOnClickListener {
                    parentFragmentManager.popBackStack(
                        "",
                        FragmentManager.POP_BACK_STACK_INCLUSIVE
                    )
                }

                if (weather.isFavourite) {
                    markAsFavourite.visibility = View.VISIBLE

                    // removing and re-adding the city to Favourites from inside of WeatherDetails
                    markAsFavourite.setOnClickListener {
                        when (weather.isFavourite) {
                            true -> {
                                markAsFavourite.setImageResource(R.drawable.ic_star_outline)
                                view.showToast("Removed from Favourites")
                                weather.isFavourite = false
                            }
                            false -> {
                                markAsFavourite.setImageResource(R.drawable.ic_star)
                                view.showToast("Added to Favourites")
                                weather.isFavourite = true
                            }
                        }

                        if (!weather.isFavourite) {
                            favouritesViewModel.removeFromFavourites(weather)
                        }
                    }
                }
                weather.city.also { city ->
                    cityName.text = city.city
                    cityCoordinates.text = String.format(
                        getString(R.string.city_coordinates),
                        city.lat.toString(),
                        city.lon.toString()
                    )
                    countryName.text = city.country
                    temperatureValue.text = weather.temperature.toString()
                    feelsLikeValue.text = weather.feelsLike.toString()
                }

                viewModel.weatherLiveData.observe(viewLifecycleOwner) { appState ->
                    when (appState) {
                        is AppState.Error -> {
                            progressBar.visibility = View.GONE
                            weatherGroup.visibility = View.INVISIBLE
                            view.showSnackBarWithAction(
                                "Something went wrong!",
                                "Reload",
                                { viewModel.loadData(weather.city.lat, weather.city.lon) })
                        }
                        is AppState.Loading -> {
                            weatherGroup.visibility = View.INVISIBLE
                            progressBar.visibility = View.VISIBLE
                        }
                        is AppState.Success -> {
                            progressBar.visibility = View.GONE
                            weatherGroup.visibility = View.VISIBLE
                            temperatureValue.text = appState.weatherData[0].temperature.toString()
                            feelsLikeValue.text = appState.weatherData[0].feelsLike.toString()
                            weatherCondition.text = appState.weatherData[0].condition
                        }
                    }

                }
                viewModel.loadData(weather.city.lat, weather.city.lon)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): WeatherDetails {
            val fragment = WeatherDetails()
            fragment.arguments = bundle
            return fragment
        }
    }
}