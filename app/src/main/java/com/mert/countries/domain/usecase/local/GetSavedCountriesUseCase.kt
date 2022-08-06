package com.mert.countries.domain.usecase.local

import com.mert.countries.utils.SavedManager
import kotlinx.coroutines.flow.flow
import java.lang.reflect.Type
import javax.inject.Inject

class GetSavedCountriesUseCase @Inject constructor(
    private val savedManager: SavedManager
) {
    suspend operator fun invoke() = getCountries()

    private suspend fun getCountries() = flow {
        val modelJson: String? = savedManager.getModelAsJson()
        val type: Type? = savedManager.getModelType()
        val modelList = savedManager.getJsonToModel(modelJson, type)
        emit(modelList)
    }
}