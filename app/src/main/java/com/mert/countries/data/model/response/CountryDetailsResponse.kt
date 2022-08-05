package com.mert.countries.data.model.response

import com.google.gson.annotations.SerializedName
import com.mert.countries.data.model.CountryDetails

data class CountryDetailsResponse(
    @SerializedName("data")
    val data: CountryDetails? = null
)
