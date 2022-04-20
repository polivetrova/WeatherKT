package com.gbhw.weatherapp.ui.main.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gbhw.weatherapp.databinding.FragmentFavouritesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentFavourites : Fragment() {

    companion object {
        fun newInstance() = FragmentFavourites()
    }

    private val viewModel: FragmentFavouritesViewModel by viewModel()
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        val recyclerView: RecyclerView = binding.favouritesRoot
        recyclerView.adapter = viewModel.adapter
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}