package com.mert.countries.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.mert.countries.R
import com.mert.countries.common.Constants
import com.mert.countries.common.Status
import com.mert.countries.data.model.Country
import com.mert.countries.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var binding: FragmentDetailBinding? = null
    private val detailViewModel: DetailViewModel by viewModels()
    private var country: Country? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        country = arguments?.getParcelable("country")
        country?.let {
            val isInSaved = detailViewModel.savedManager.isCountrySaved(it)
            if (isInSaved) {
                binding?.ivSaved?.setImageResource(R.drawable.ic_star)
            } else {
                binding?.ivSaved?.setImageResource(R.drawable.ic_empty_star)
            }
        }

        with(binding) {
            this?.ivSaved?.setOnClickListener {
                country?.let {
                    val isInSaved = detailViewModel.savedManager.isCountrySaved(it)
                    if (isInSaved) {
                        detailViewModel.savedManager.removeCountry(it)
                    } else {
                        detailViewModel.savedManager.addCountry(it)
                    }
                }
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        detailViewModel.savedManager.getSavedLiveData()?.observe(viewLifecycleOwner) {
            country?.let {
                val isInSaved = detailViewModel.savedManager.isCountrySaved(it)
                if (isInSaved) {
                    binding?.ivSaved?.setImageResource(R.drawable.ic_star)
                } else {
                    binding?.ivSaved?.setImageResource(R.drawable.ic_empty_star)
                }
            }
        }

        detailViewModel.fetchCountryDetail(country?.code.orEmpty())
            .observe(viewLifecycleOwner) { countryDetailResponse ->
                when (countryDetailResponse.status) {
                    Status.SUCCESS -> {
                        binding?.let {
                            ivCountryFlag?.loadUrl(
                                countryDetailResponse.data?.data?.flagImageUri?.replace(
                                    "http",
                                    "https"
                                )
                            )
                            tvTitle?.text = resources.getString(
                                R.string.country_name,
                                countryDetailResponse.data?.data?.name
                            )
                            tvCountryCode?.text =
                                resources.getString(R.string.country_code, country?.code)
                            btnWiki?.setOnClickListener {
                                val queryUrl: Uri =
                                    Uri.parse("${Constants.WIKI_DATA_URL}${countryDetailResponse.data?.data?.wikiDataId}")
                                val intent = Intent(Intent.ACTION_VIEW, queryUrl)
                                context?.startActivity(intent)
                            }
                            detailLayout?.visibility = View.VISIBLE
                        }
                    }
                    Status.LOADING -> {
                        binding?.detailLayout?.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        binding?.detailLayout?.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            countryDetailResponse.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
    }

    private fun ImageView.loadUrl(url: String?) {
        val imageLoader = ImageLoader.Builder(context)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()
        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(500)
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_error)
            .data(url)
            .target(this)
            .build()

        imageLoader.enqueue(request)
    }
}