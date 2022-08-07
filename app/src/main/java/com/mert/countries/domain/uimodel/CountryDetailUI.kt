package com.mert.countries.domain.uimodel

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.mert.countries.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryDetailUI(
    val countryName: String? = null,
    val countryCode: String? = null,
    val flagImageUri: String? = null,
    val wikiDataUrl: String? = null,
    @DrawableRes val drawableResource: Int? = R.drawable.ic_empty_star,
    val isSaved: Boolean? = false
) : Parcelable