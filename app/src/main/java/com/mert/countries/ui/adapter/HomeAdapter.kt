package com.mert.countries.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mert.countries.R
import com.mert.countries.data.model.Country
import com.mert.countries.databinding.ItemCountryBinding
import com.mert.countries.utils.ListActions
import com.mert.countries.utils.SavedManager

class HomeAdapter constructor(
    private val listActions: ListActions,
    private val savedManager: SavedManager
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var countries: ArrayList<Country> = ArrayList()

    class HomeViewHolder(itemView: View, val savedManager: SavedManager) :
        RecyclerView.ViewHolder(itemView) {
        val binding = ItemCountryBinding.bind(itemView)
        fun bind(country: Country) {
            with(binding) {
                tvTitle.text = country.name
                val isSaved = savedManager.isCountrySaved(country)
                if (isSaved) {
                    ivSaved.setImageResource(R.drawable.ic_star)
                } else {
                    ivSaved.setImageResource(R.drawable.ic_empty_star)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false),
        savedManager
    )

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        with(holder) {
            bind(countries[position])
            binding.apply {
                tvTitle.setOnClickListener {
                    listActions.onClickCountry(countries[position])
                }
                ivSaved.setOnClickListener {
                    val isInSaved = savedManager.isCountrySaved(countries[position])
                    if (isInSaved) {
                        savedManager.removeCountry(countries[position])
                    } else {
                        savedManager.addCountry(countries[position])
                    }
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun getItemCount(): Int = countries.size

    fun updateList(countries: List<Country>?) {
        this.countries.apply {
            clear()
            countries?.let { addAll(it) }
        }
    }
}