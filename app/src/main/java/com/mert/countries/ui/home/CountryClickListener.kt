package com.mert.countries.ui.home

import com.mert.countries.domain.uimodel.CountryUI

interface CountryClickListener {
    fun onClickCountry(country: CountryUI)
    fun checkIfCountryIsSaved(countryCode: String): Boolean
    fun removeCountry(country: CountryUI): Boolean
    fun addCountry(country: CountryUI): Boolean
}