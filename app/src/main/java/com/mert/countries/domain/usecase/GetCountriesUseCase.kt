package com.mert.countries.domain.usecase

import com.mert.countries.domain.repository.CountryRepository
import com.mert.countries.domain.uimodel.CountryUI
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val countryRepository: CountryRepository,
) {
    operator fun invoke() = countryRepository.getCountries().map {
        val countryUiList = it.data?.map { country ->
            CountryUI(
                countryId = country.code,
                title = country.name
            )
        }
        countryUiList
    }
}