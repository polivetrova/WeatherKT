package com.gbhw.weatherapp.ui.main.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.gbhw.weatherapp.R
import com.gbhw.weatherapp.databinding.FragmentDetailsBinding
import com.gbhw.weatherapp.model.DetailsService
import com.gbhw.weatherapp.model.LATITUDE_EXTRA
import com.gbhw.weatherapp.model.LONGITUDE_EXTRA
import com.gbhw.weatherapp.model.entities.getDefaultCity
import com.gbhw.weatherapp.model.entities.restEntites.FactDTO
import com.gbhw.weatherapp.model.entities.restEntites.WeatherDTO
import com.gbhw.weatherapp.ui.main.weatherDetails.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentHome : Fragment() {

    // геолокация???

    companion object {
        fun newInstance() = FragmentHome()
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val city = getDefaultCity()
    private lateinit var weatherDTO: WeatherDTO

    private val loadResultsReceiver: BroadcastReceiver = object :
        BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.getStringExtra(DETAILS_LOAD_RESULT_EXTRA)) {
                DETAILS_RESPONSE_SUCCESS_EXTRA -> {
                    weatherDTO = WeatherDTO(
                        FactDTO(
                            intent.getIntExtra(DETAILS_TEMP_EXTRA, TEMP_INVALID),
                            intent.getIntExtra(DETAILS_FEELS_LIKE_EXTRA, FEELS_LIKE_INVALID),
                            intent.getStringExtra(DETAILS_CONDITION_EXTRA)
                        )
                    )
                    renderData()
                }
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            it.startService(Intent(it, DetailsService::class.java).apply {
                putExtra(
                    LATITUDE_EXTRA,
                    city.lat
                )
                putExtra(
                    LONGITUDE_EXTRA,
                    city.lon
                )
            })
        }
    }

    private fun renderData() = with(binding) {
        progressBar.visibility = View.GONE
        weatherGroup.visibility = View.VISIBLE
        cityName.text = city.city
        cityCoordinates.text = String.format(
            getString(R.string.city_coordinates),
            city.lat.toString(),
            city.lon.toString()
        )
        countryName.text = city.country
        weatherCondition.text = weatherDTO.fact.condition
        temperatureValue.text = weatherDTO.fact.temp.toString()
        feelsLikeValue.text = weatherDTO.fact.feelsLike.toString()
    }

    override fun onDestroy() {
        _binding = null
        context?.let {
            LocalBroadcastManager.getInstance(it).unregisterReceiver(loadResultsReceiver)
        }
        super.onDestroy()
    }
}