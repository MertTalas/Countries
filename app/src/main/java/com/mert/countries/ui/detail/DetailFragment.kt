package com.mert.countries.ui.detail

import androidx.fragment.app.Fragment
import com.mert.countries.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
/*
    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val detailViewModel: DetailViewModel by viewModels()
    private var country: Country? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        country = arguments?.getParcelable("country")

        country?.let {
            val isInSaved = detailViewModel.savedManager.countryTempInSaved(it)
            if (isInSaved)
                binding.ivSaved.setImageResource(R.drawable.ic_star)
            else
                binding.ivSaved.setImageResource(R.drawable.ic_empty_star)
        }

        with(binding) {
            ivSaved.setOnClickListener {
                country?.let {
                    val isInSaved = detailViewModel.savedManager.countryInSaved(it)

                    if (isInSaved)
                        detailViewModel.savedManager.removeCountry(it)
                    else
                        detailViewModel.savedManager.setCountry(it)
                }
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        detailViewModel.savedManager.isOperationSucceeded.observe(viewLifecycleOwner) {
            country?.let {
                val isInSaved = detailViewModel.savedManager.countryTempInSaved(it)
                if (isInSaved)
                    binding.ivSaved.setImageResource(R.drawable.ic_star)
                else
                    binding.ivSaved.setImageResource(R.drawable.ic_empty_star)
            }
        }

        detailViewModel.fetchCountryDetail(country?.code.orEmpty())
            .observe(viewLifecycleOwner) { countryDetailResponse ->
                when (countryDetailResponse.status) {
                    Status.SUCCESS -> {
                        with(binding) {
                            ivCountryFlag.loadUrl(
                                countryDetailResponse.data?.data?.flagImageUri?.replace(
                                    "http",
                                    "https"
                                )
                            )
                            tvTitle.text = resources.getString(
                                R.string.country_name,
                                countryDetailResponse.data?.data?.name
                            )
                            tvCountryCode.text =
                                resources.getString(R.string.country_code, country?.code)
                            btnWiki.setOnClickListener {
                                val queryUrl: Uri =
                                    Uri.parse("${Constants.WIKI_DATA_URL}${countryDetailResponse.data?.data?.wikiDataId}")
                                val intent = Intent(Intent.ACTION_VIEW, queryUrl)
                                context?.startActivity(intent)
                            }
                            detailLayout.visibility = View.VISIBLE
                        }
                    }
                    Status.LOADING -> {
                        with(binding) {
                            detailLayout.visibility = View.GONE
                        }
                    }
                    Status.ERROR -> {
                        binding.detailLayout.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            countryDetailResponse.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
    }*/
}