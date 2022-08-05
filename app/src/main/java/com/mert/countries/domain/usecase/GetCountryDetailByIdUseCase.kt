package com.mert.countries.domain.usecase

import com.mert.countries.domain.repository.CountryRepository
import javax.inject.Inject

class GetCountryDetailByIdUseCase @Inject constructor(
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke(countryId: String) =
        countryRepository.getCountryDetailById(countryId)
}