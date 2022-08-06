package com.mert.countries.domain.repository

import com.mert.countries.data.model.response.CountryDetailsResponse
import com.mert.countries.data.model.response.CountryResponse
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    fun getCountries(): Flow<CountryResponse>
    fun getCountryDetailById(countryId: String): Flow<CountryDetailsResponse>
}