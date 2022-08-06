package com.mert.countries.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.mert.countries.R
import com.mert.countries.databinding.FragmentHomeBinding
import com.mert.countries.domain.uimodel.CountryUI
import com.mert.countries.ui.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), CountryClickListener {

    private var binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter

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
        viewModel.initVM()
        homeAdapter = HomeAdapter(this)
        binding?.let {
            rvCountries.adapter = homeAdapter
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countryList.observe(viewLifecycleOwner) {
            if (it != null) {
                homeAdapter.updateList(it)
                binding?.rvCountries?.adapter?.notifyDataSetChanged()
            }

            binding?.pbLoading?.visibility = View.GONE
            binding?.rvCountries?.visibility = View.VISIBLE
        }


/*
        viewModel.fetchCountries().observe(viewLifecycleOwner) {
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
*/
    }

    override fun onClickCountry(country: CountryUI) {
        val bundle = Bundle()
        bundle.putParcelable("country", country)
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    // TODO: resource üzerinden resource return et
    override fun checkIfCountryIsSaved(countryCode: String): Boolean {
        viewModel.test()
        return true
    }

    override fun removeCountry(country: CountryUI): Boolean {
        return true
    }

    override fun addCountry(country: CountryUI): Boolean {
        return true
    }
}