package com.mert.countries.data.repository

import com.mert.countries.common.Constants
import com.mert.countries.data.model.response.CountryDetailsResponse
import com.mert.countries.data.model.response.CountryResponse
import com.mert.countries.data.remote.CityService
import com.mert.countries.domain.repository.CountryRepository
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val cityService: CityService
) : CountryRepository {
    override suspend fun getCountries(): CountryResponse = cityService.getCountries(Constants.LIMIT)
    override suspend fun getCountryDetailById(countryId: String): CountryDetailsResponse =
        cityService.getCountryDetailById(countryId)
}