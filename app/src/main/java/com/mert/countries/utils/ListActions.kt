package com.mert.countries.utils

import com.mert.countries.data.model.Country

interface ListActions {
    fun onClickCountry(country: Country)
}