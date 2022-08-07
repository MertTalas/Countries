package com.mert.countries.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.mert.countries.R
import com.mert.countries.data.model.Country
import com.mert.countries.databinding.FragmentSavedBinding
import com.mert.countries.ui.adapter.SavedAdapter
import com.mert.countries.utils.ListActions
import com.mert.countries.utils.SavedManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_saved.*
import javax.inject.Inject

@AndroidEntryPoint
class SavedFragment : Fragment(R.layout.fragment_saved), ListActions {

    private var binding: FragmentSavedBinding? = null

    @Inject
    lateinit var savedManager: SavedManager
    private val savedAdapter: SavedAdapter by lazy {
        SavedAdapter(this, savedManager)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentSavedBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val countries = savedManager.getCountries() ?: arrayListOf()
        if (countries.isEmpty()) {
            rvCountries.visibility = View.GONE
            pbLoadingSaved.visibility = View.VISIBLE
        } else {
            rvCountries.visibility = View.VISIBLE
            pbLoadingSaved.visibility = View.GONE
        }

        savedManager.getSavedLiveData()?.observe(viewLifecycleOwner) {
            savedAdapter.setData(savedManager.getCountries() ?: arrayListOf())
            if (countries.isEmpty()) {
                rvCountries.visibility = View.GONE
                pbLoadingSaved.visibility = View.VISIBLE
            } else {
                rvCountries.visibility = View.VISIBLE
                pbLoadingSaved.visibility = View.GONE
            }
        }
        binding?.rvCountries?.apply {
            adapter = savedAdapter
        }
    }

    override fun onClickCountry(country: Country) {
        goToCountryDetails(country)
    }

    private fun goToCountryDetails(country: Country) {
        binding?.let {
            Navigation.findNavController(it.rvCountries)
                .navigate(SavedFragmentDirections.actionSavedFragmentToDetailFragment(country))
        }
    }
}