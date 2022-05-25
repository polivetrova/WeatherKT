package com.gbhw.weatherapp.ui.main.weatherDetails

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.gbhw.weatherapp.R
import com.gbhw.weatherapp.databinding.FragmentDetailsBinding
import com.gbhw.weatherapp.model.DetailsService
import com.gbhw.weatherapp.model.LATITUDE_EXTRA
import com.gbhw.weatherapp.model.LONGITUDE_EXTRA
import com.gbhw.weatherapp.model.entities.Weather
import com.gbhw.weatherapp.model.entities.restEntites.FactDTO
import com.gbhw.weatherapp.model.entities.restEntites.WeatherDTO
import com.gbhw.weatherapp.ui.main.showToast

const val DETAILS_INTENT_FILTER = "DETAILS INTENT FILTER"
const val DETAILS_LOAD_RESULT_EXTRA = "LOAD RESULT"
const val DETAILS_REQUEST_ERROR_EXTRA = "REQUEST ERROR"
const val DETAILS_REQUEST_ERROR_MESSAGE_EXTRA = "REQUEST ERROR MESSAGE"
const val DETAILS_RESPONSE_SUCCESS_EXTRA = "RESPONSE SUCCESS"
const val DETAILS_TEMP_EXTRA = "TEMPERATURE"
const val DETAILS_FEELS_LIKE_EXTRA = "FEELS LIKE"
const val DETAILS_CONDITION_EXTRA = "CONDITION"
private const val TEMP_INVALID = -100
private const val FEELS_LIKE_INVALID = -100

class WeatherDetails : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherBundle: Weather

    private val loadResultsReceiver: BroadcastReceiver = object :
        BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.getStringExtra(DETAILS_LOAD_RESULT_EXTRA)) {
                DETAILS_RESPONSE_SUCCESS_EXTRA -> renderData(
                    WeatherDTO(
                        FactDTO(
                            intent.getIntExtra(DETAILS_TEMP_EXTRA, TEMP_INVALID),
                            intent.getIntExtra(DETAILS_FEELS_LIKE_EXTRA, FEELS_LIKE_INVALID),
                            intent.getStringExtra(DETAILS_CONDITION_EXTRA)
                        )
                    )
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            LocalBroadcastManager.getInstance(it)
                .registerReceiver(
                    loadResultsReceiver,
                    IntentFilter(DETAILS_INTENT_FILTER)
                )
        }
    }

    override fun onDestroy() {
        context?.let {
            LocalBroadcastManager.getInstance(it).unregisterReceiver(loadResultsReceiver)
        }
        super.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Weather()
        getWeather()
    }

    private fun getWeather() = with(binding) {
        progressBar.visibility = View.GONE
        weatherGroup.visibility = View.VISIBLE
        context?.let {
            it.startService(Intent(it, DetailsService::class.java).apply {
                putExtra(
                    LATITUDE_EXTRA,
                    weatherBundle.city.lat
                )
                putExtra(
                    LONGITUDE_EXTRA,
                    weatherBundle.city.lon
                )
            })
        }
    }

    private fun renderData(weatherDTO: WeatherDTO) = with(binding) {
        weatherGroup.visibility = View.VISIBLE
        progressBar.visibility = View.GONE

        backArrow.visibility = View.VISIBLE
        backArrow.setOnClickListener {
            parentFragmentManager.popBackStack(
                "",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
        if (weatherBundle.isFavourite) {
            markAsFavourite.visibility = View.VISIBLE
            // removing and re-adding the city to Favourites from inside of WeatherDetails
            markAsFavourite.setOnClickListener {
                when (weatherBundle.isFavourite) {
                    true -> {
                        markAsFavourite.setImageResource(R.drawable.ic_star_outline)
                        view?.showToast("Removed from Favourites")
                        weatherBundle.isFavourite = false
                    }
                    false -> {
                        markAsFavourite.setImageResource(R.drawable.ic_star)
                        view?.showToast("Added to Favourites")
                        weatherBundle.isFavourite = true
                    }
                }
            }
        }
        val fact = weatherDTO.fact
        val temp = fact.temp
        val feelsLike = fact.feelsLike
        val condition = fact.condition

        val city = weatherBundle.city
        binding.cityName.text = city.city
        binding.cityCoordinates.text = String.format(
            getString(R.string.city_coordinates),
            city.lat.toString(),
            city.lon.toString()
        )
        binding.temperatureValue.text = temp.toString()
        binding.feelsLikeValue.text = feelsLike.toString()
        binding.weatherCondition.text = condition
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
