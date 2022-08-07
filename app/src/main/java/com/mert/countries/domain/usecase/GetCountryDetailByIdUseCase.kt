package com.mert.countries.domain.usecase

import com.mert.countries.domain.repository.CountryRepository
import com.mert.countries.domain.uimodel.CountryDetailUI
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCountryDetailByIdUseCase @Inject constructor(
    private val countryRepository: CountryRepository
) {
    operator fun invoke(countryId: String) = countryRepository.getCountryDetailById(countryId).map {
        val countryDetail = CountryDetailUI(
            countryName = it.data?.name,
            countryCode = it.data?.code,
            flagImageUri = it.data?.flagImageUri,
            wikiDataUrl = it.data?.wikiDataId,
        )
        countryDetail
    }
}