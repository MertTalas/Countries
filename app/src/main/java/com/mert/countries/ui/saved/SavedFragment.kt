package com.mert.countries.ui.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.mert.countries.R
import com.mert.countries.data.model.Country
import com.mert.countries.databinding.FragmentSavedBinding
import com.mert.countries.ui.adapter.SavedAdapter
import com.mert.countries.utils.ListActions
import com.mert.countries.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : Fragment(R.layout.fragment_saved), ListActions {

    private val binding by viewBinding(FragmentSavedBinding::bind)
    private val savedViewModel: SavedViewModel by viewModels()
    private val savedAdapter: SavedAdapter by lazy {
        SavedAdapter(this, savedViewModel.savedManager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val countries = savedViewModel.savedManager.getCountries() ?: arrayListOf()

        with(binding) {
            if (countries.isEmpty()) {
                rvCountries.visibility = View.GONE
                pbLoadingSaved.visibility = View.VISIBLE
            } else {
                rvCountries.visibility = View.VISIBLE
                pbLoadingSaved.visibility = View.GONE
            }
        }
        savedViewModel.savedManager.getSavedLiveData()?.observe(viewLifecycleOwner) {
            savedAdapter.setData(savedViewModel.savedManager.getCountries() ?: arrayListOf())

            with(binding) {
                if (countries.isEmpty()) {
                    rvCountries.visibility = View.GONE
                    pbLoadingSaved.visibility = View.VISIBLE
                } else {
                    rvCountries.visibility = View.VISIBLE
                    pbLoadingSaved.visibility = View.GONE
                }
            }
        }

        binding.rvCountries.apply {
            adapter = savedAdapter
        }
    }

    override fun onClickCountry(country: Country) {
        val bundle = Bundle()
        bundle.putParcelable("country", country)
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_savedFragment_to_detailFragment, bundle)
        }
    }
}