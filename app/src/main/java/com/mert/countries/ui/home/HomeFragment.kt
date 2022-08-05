package com.mert.countries.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.mert.countries.R
import com.mert.countries.common.Status
import com.mert.countries.data.model.Country
import com.mert.countries.databinding.FragmentHomeBinding
import com.mert.countries.ui.adapter.HomeAdapter
import com.mert.countries.utils.ListActions
import com.mert.countries.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), ListActions {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val homeViewModel: HomeViewModel by viewModels()
    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter(this,homeViewModel.savedManager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCountries.apply {
            adapter = homeAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        homeViewModel.fetchCountries().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.pbLoading.visibility = View.GONE
                    it.data?.let { countryData -> homeAdapter.updateList(countryData.data) }
                    binding.rvCountries.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.pbLoading.visibility = View.VISIBLE
                    binding.rvCountries.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.rvCountries.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onClickCountry(country: Country) {
        val bundle = Bundle()
        bundle.putParcelable("country", country)
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }
    }
}