package com.mert.countries.domain.usecase

import com.mert.countries.domain.repository.CountryRepository
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke() = countryRepository.getCountries()
}