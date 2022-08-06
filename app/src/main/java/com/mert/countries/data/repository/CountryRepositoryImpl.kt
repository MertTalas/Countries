package com.mert.countries.data.repository

import com.mert.countries.common.Constants
import com.mert.countries.data.model.response.CountryDetailsResponse
import com.mert.countries.data.model.response.CountryResponse
import com.mert.countries.data.remote.CityService
import com.mert.countries.domain.repository.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val citiesAPI: CityService
) : CountryRepository {
    override fun getCountries(): Flow<CountryResponse> = sendRequest {
        citiesAPI.getCountries(Constants.LIMIT)
    }

    override fun getCountryDetailById(countryId: String): Flow<CountryDetailsResponse> = flow {
        val response = citiesAPI.getCountryDetailById(countryId)
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun <T> sendRequest(call: suspend () -> T): Flow<T> = flow {
        val response = call.invoke()
        emit(response)
    }.flowOn(Dispatchers.IO)
}