package com.mert.countries.ui.saved

import androidx.fragment.app.Fragment
import com.mert.countries.R
import com.mert.countries.data.model.Country
import com.mert.countries.utils.ListActions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : Fragment(R.layout.fragment_saved), ListActions {
    /*
    private val binding by viewBinding(FragmentSavedBinding::bind)
    private val savedViewModel: SavedViewModel by viewModels()
    private val savedAdapter: SavedAdapter by lazy {
        SavedAdapter(this, savedViewModel.savedManager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val countries = savedViewModel.savedManager.getTempCountries() ?: arrayListOf()

        with(binding) {
            if (countries.isEmpty()) {
                rvCountries.visibility = View.GONE
                pbLoadingSaved.visibility = View.VISIBLE
            } else {
                rvCountries.visibility = View.VISIBLE
                pbLoadingSaved.visibility = View.GONE
            }
        }
        savedViewModel.savedManager.isOperationSucceeded.observe(viewLifecycleOwner) {
            savedAdapter.setData(savedViewModel.savedManager.getTempCountries() ?: arrayListOf())

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
    }*/
    override fun onClickCountry(country: Country) {
        TODO("Not yet implemented")
    }
}