package com.mert.countries.data.remote

import com.mert.countries.data.model.response.CountryResponse
import com.mert.countries.data.model.response.CountryDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface CityService {
    @GET("countries")
    suspend fun getCountries(
        @Query("limit") limit: String
    ): CountryResponse

    @GET("countries/{countryId}")
    suspend fun getCountryDetailById(
        @Path("countryId") countryId: String,
    ): CountryDetailsResponse
}