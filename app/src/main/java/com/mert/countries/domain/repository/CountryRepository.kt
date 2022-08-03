package com.mert.countries.domain.repository

import com.mert.countries.data.model.country.CountryResponse
import com.mert.countries.data.model.countrydetail.CountryDetailsResponse

interface CountryRepository {

    suspend fun getCountries(): CountryResponse
    suspend fun getCountryDetailById(countryId: String): CountryDetailsResponse
}