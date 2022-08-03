package com.mert.countries.data.remote

import com.mert.countries.data.model.country.CountryResponse
import com.mert.countries.data.model.countrydetail.CountryDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface CitiesAPI {

    @GET("countries")
    suspend fun getCountries(
        @Header("X-RapidAPI-Key") apiKey: String,
        @Query("limit") limit: String
    ): CountryResponse

    @GET("countries/{countryId}")
    suspend fun getCountryDetailById(
        @Header("X-RapidAPI-Key") apiKey: String,
        @Path("countryId") countryId: String,
    ): CountryDetailsResponse

}