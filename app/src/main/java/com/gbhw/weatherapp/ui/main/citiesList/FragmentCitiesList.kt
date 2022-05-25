package com.gbhw.weatherapp.ui.main.citiesList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gbhw.weatherapp.R
import com.gbhw.weatherapp.databinding.FragmentCitiesListBinding
import com.gbhw.weatherapp.model.entities.Weather
import com.gbhw.weatherapp.ui.main.weatherDetails.WeatherDetails
import com.gbhw.weatherapp.ui.main.favourites.FragmentFavouritesViewModel
import com.gbhw.weatherapp.ui.main.showToast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentCitiesList : Fragment() {
    companion object {
        fun newInstance() = FragmentCitiesList()
    }

    private val viewModel: FragmentCitiesListViewModel by viewModel()
    private val favouritesViewModel by sharedViewModel<FragmentFavouritesViewModel>()
    private var _binding: FragmentCitiesListBinding? = null
    private val binding get() = _binding!!
    private var adapter: CitiesListRecyclerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCitiesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            citiesListRoot.adapter = getAdapter()
            adapter?.setWeather(getWeatherDataSet())
        }
    }

    private fun getAdapter(): CitiesListRecyclerAdapter {
        with(binding) {
            adapter = CitiesListRecyclerAdapter(object : OnItemViewClickListener {
                override fun onItemViewClick(weather: Weather) {
                    val manager = activity?.supportFragmentManager
                    manager?.let { _ ->
                        val bundle = Bundle().apply {
                            putParcelable(WeatherDetails.BUNDLE_EXTRA, weather)
                        }
                        manager.beginTransaction()
                            .add(R.id.container, WeatherDetails.newInstance(bundle))
                            .addToBackStack("")
                            .commitAllowingStateLoss()
                    }
                }

                override fun onFavouriteButtonClick(weather: Weather) {
                    when (weather.isFavourite) {
                        true -> {
                            favouritesViewModel.addToFavourites(weather)
                            view?.showToast("Added to Favourites")
                        }
                        false -> {
                            favouritesViewModel.removeFromFavourites(weather)
                            view?.showToast("Removed from Favourites")
                        }
                    }
                }
            }).apply {
                setWeather(getWeatherDataSet())
            }
            citiesListRoot.adapter = adapter
        }
        return adapter as CitiesListRecyclerAdapter
    }

    private fun getWeatherDataSet() = viewModel.getCitiesList()

    interface OnItemViewClickListener {
        fun onItemViewClick(weather: Weather)
        fun onFavouriteButtonClick(weather: Weather)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}