package com.mert.countries.data.model.country

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("data")
    val data: List<Country>
)
