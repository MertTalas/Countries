package com.mert.countries.data.model.response

import com.google.gson.annotations.SerializedName
import com.mert.countries.data.model.Country

data class CountryResponse(
    @SerializedName("data")
    val data: List<Country>? = null
)
