package com.mert.countries.domain.uimodel

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.mert.countries.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryUI(
    val countryId: String? = null,
    val title: String? = null,
    @DrawableRes val drawableResource: Int? = R.drawable.ic_empty_star,
    val isSaved: Boolean? = false
) : Parcelable
