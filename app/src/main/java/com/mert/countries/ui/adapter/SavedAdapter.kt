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

class SavedAdapter(
    private val listActions: ListActions,
    private val savedManager: SavedManager
) : RecyclerView.Adapter<SavedAdapter.ItemViewHolder>() {

    private var countriesInSaved = savedManager.getCountries() ?: arrayListOf()

    class ItemViewHolder(
        itemView: View,
        private val listActions: ListActions,
        val savedManager: SavedManager
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCountryBinding.bind(itemView)

        fun bind(country: Country) {
            with(binding) {
                tvTitle.text = country.name
                itemLayout.setOnClickListener {
                    listActions.onClickCountry(country)
                }
                ivSaved.setImageResource(com.mert.countries.R.drawable.ic_star)
                ivSaved.setOnClickListener {
                    savedManager.removeCountry(country)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false),
        listActions,
        savedManager
    )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentCountry = countriesInSaved[position]
        holder.bind(currentCountry)
    }

    override fun getItemCount(): Int = countriesInSaved.size

    fun setData(countries: ArrayList<Country>) {
        this.countriesInSaved = countries
        notifyDataSetChanged()
    }
}