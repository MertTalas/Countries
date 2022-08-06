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
) : RecyclerView.Adapter<SavedAdapter.ItemViewHolder>(){

    private var countriesInSaved = savedManager.getTempCountries() ?: arrayListOf()

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemCountryBinding.bind(itemView)

        fun bind(country: Country) {
            binding.tvTitle.text = country.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
    )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(countriesInSaved[position])

        with(holder.binding) {
            noteLayout.setOnClickListener {
                listActions.onClickCountry(countriesInSaved[position])
            }

            ivSaved.setImageResource(R.drawable.ic_star)

            ivSaved.setOnClickListener {
                savedManager.removeTempCountry(countriesInSaved[position])
            }
        }
    }

    override fun getItemCount(): Int = countriesInSaved.size

    fun setData(countries: ArrayList<Country>) {
        this.countriesInSaved = countries
        notifyDataSetChanged()
    }
}