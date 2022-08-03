package com.mert.countries.data.model.countrydetail

import com.google.gson.annotations.SerializedName

data class CountryDetailsResponse(
    @SerializedName("data")
    val data: CountryDetails
)
