package com.mert.countries.data.model.countrydetail

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryDetails(
    @SerializedName("capital")
    val capital: String? = null,
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("callingCode")
    val callingCode: String? = null,
    @SerializedName("currencyCodes")
    val currencyCodes: List<String>? = null,
    @SerializedName("flagImageUri")
    val flagImageUri: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("numRegions")
    val numRegions: String? = null,
    @SerializedName("wikiDataId")
    val wikiDataId: String? = null
) : Parcelable
