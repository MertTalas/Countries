package com.mert.countries.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.mert.countries.R
import com.mert.countries.common.Status
import com.mert.countries.data.model.Country
import com.mert.countries.databinding.FragmentHomeBinding
import com.mert.countries.ui.adapter.HomeAdapter
import com.mert.countries.utils.ListActions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), ListActions {

    private var binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModels()
    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter(this, homeViewModel.savedManager)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.rvCountries?.apply {
            adapter = homeAdapter
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        homeViewModel.fetchCountries().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.pbLoading?.visibility = View.GONE
                    it.data?.let { countryData -> homeAdapter.updateList(countryData.data) }
                    binding?.rvCountries?.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding?.pbLoading?.visibility = View.VISIBLE
                    binding?.rvCountries?.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding?.rvCountries?.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onClickCountry(country: Country) {
        goToCountryDetails(country)
    }

    private fun goToCountryDetails(country: Country) {
        binding?.let {
            Navigation.findNavController(it.rvCountries)
                .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(country))
        }
    }
}