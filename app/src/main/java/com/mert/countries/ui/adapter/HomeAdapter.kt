package com.mert.countries.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mert.countries.R
import com.mert.countries.databinding.ItemCountryBinding
import com.mert.countries.domain.uimodel.CountryUI
import com.mert.countries.ui.home.CountryClickListener

class HomeAdapter constructor(
    private val countryClickListener: CountryClickListener
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var countries: ArrayList<CountryUI> = ArrayList()

    class HomeViewHolder(
        itemView: View,
        private val countryClickListener: CountryClickListener
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCountryBinding.bind(itemView)

        fun bind(country: CountryUI) {
            with(binding) {
                tvTitle.text = country.title
                tvTitle.setOnClickListener {
                    countryClickListener.onClickCountry(country)
                }

                val isSaved = checkIfCountryIsSaved(country)
                val starIcon = if (isSaved) {
                    R.drawable.ic_star
                } else {
                    R.drawable.ic_empty_star
                }

                ivSaved.setImageResource(starIcon)
                ivSaved.setOnClickListener {
                    if (isSaved) {
                        removeCountry(country)
                    } else {
                        addCountry(country)
                    }
                }
            }
        }

        private fun checkIfCountryIsSaved(country: CountryUI) =
            countryClickListener.checkIfCountryIsSaved(country.countryId ?: "")

        private fun removeCountry(country: CountryUI) = countryClickListener.removeCountry(country)

        private fun addCountry(country: CountryUI) = countryClickListener.addCountry(country)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false),
        countryClickListener
    )

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val currentCountry = countries[position]
        holder.bind(currentCountry)
    }

    override fun getItemCount(): Int = countries.size

    fun updateList(countries: List<CountryUI>) {
        this.countries.apply {
            clear()
            addAll(countries)
        }
    }
}