package com.mert.countries.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("currencyCodes")
    val currencyCodes: List<String>? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("wikiDataId")
    val wikiDataId: String? = null
) : Parcelable
