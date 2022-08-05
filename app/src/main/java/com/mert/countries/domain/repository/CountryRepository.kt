package com.mert.countries.domain.repository

import com.mert.countries.data.model.response.CountryResponse
import com.mert.countries.data.model.response.CountryDetailsResponse

interface CountryRepository {
    suspend fun getCountries(): CountryResponse
    suspend fun getCountryDetailById(countryId: String): CountryDetailsResponse
}