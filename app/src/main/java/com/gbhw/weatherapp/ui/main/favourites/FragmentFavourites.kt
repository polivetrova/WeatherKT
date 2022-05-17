package com.gbhw.weatherapp.ui.main.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gbhw.weatherapp.R
import com.gbhw.weatherapp.databinding.FragmentFavouritesBinding
import com.gbhw.weatherapp.model.entities.Weather
import com.gbhw.weatherapp.ui.main.WeatherDetails
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentFavourites : Fragment() {

    companion object {
        fun newInstance() = FragmentFavourites()
    }

    private val viewModel: FragmentFavouritesViewModel by viewModel()
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!
    private var adapter: FavouritesRecyclerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            favouritesRoot.adapter = getAdapter()
        }
    }

    private fun getAdapter(): FavouritesRecyclerAdapter {
        with(binding) {

            adapter = FavouritesRecyclerAdapter(object : OnItemViewClickListener {
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
            }).apply {
                setWeather(getWeatherDataSet())
            }
            favouritesRoot.adapter = adapter
        }
        return adapter as FavouritesRecyclerAdapter
    }

    private fun getWeatherDataSet() = viewModel.getCitiesList()

    interface OnItemViewClickListener {
        fun onItemViewClick(weather: Weather)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}