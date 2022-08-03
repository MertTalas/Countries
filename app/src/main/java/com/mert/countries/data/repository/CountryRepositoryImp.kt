package com.mert.countries.data.repository

import com.mert.countries.common.Constants
import com.mert.countries.data.remote.CitiesAPI
import com.mert.countries.data.model.country.CountryResponse
import com.mert.countries.data.model.countrydetail.CountryDetailsResponse
import com.mert.countries.domain.repository.CountryRepository
import javax.inject.Inject

class CountryRepositoryImp @Inject constructor(
    private val citiesAPI: CitiesAPI
) : CountryRepository {

    override suspend fun getCountries(): CountryResponse = citiesAPI.getCountries(Constants.API_KEY, Constants.LIMIT)

    override suspend fun getCountryDetailById(countryId: String): CountryDetailsResponse = citiesAPI.getCountryDetailById(Constants.API_KEY, countryId)
}