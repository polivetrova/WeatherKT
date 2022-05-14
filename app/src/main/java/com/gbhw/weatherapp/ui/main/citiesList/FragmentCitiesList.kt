package com.gbhw.weatherapp.ui.main.citiesList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gbhw.weatherapp.databinding.FragmentCitiesListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentCitiesList : Fragment() {

    companion object {
        fun newInstance() = FragmentCitiesList()
    }

    private val viewModel: FragmentCitiesListViewModel by viewModel()
    private var _binding: FragmentCitiesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCitiesListBinding.inflate(inflater, container, false)
        val recyclerView: RecyclerView = binding.citiesListRoot
        recyclerView.adapter = viewModel.adapter
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}