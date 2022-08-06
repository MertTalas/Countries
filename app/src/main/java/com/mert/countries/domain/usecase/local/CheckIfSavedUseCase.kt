package com.mert.countries.domain.usecase.local

import com.mert.countries.utils.SavedManager
import kotlinx.coroutines.flow.flow
import java.lang.reflect.Type
import javax.inject.Inject

class CheckIfSavedUseCase @Inject constructor(
    private val savedManager: SavedManager
) {
    data class Params(
        val countryCode: List<String>
    )

    suspend operator fun invoke(params: Params) = checkIfSaved2(params.countryCode)

    private suspend fun checkIfSaved(countryCode: String) = flow {
        val modelJson: String? = savedManager.getModelAsJson()
        val type: Type? = savedManager.getModelType()
        val modelList = savedManager.getJsonToModel(modelJson, type)

        val requiredCountry = modelList?.firstOrNull { it.code == countryCode }

        if (requiredCountry == null) {
            emit(false)
        } else {
            emit(true)
        }
    }

    private suspend fun checkIfSaved2(countryCode: List<String>) = flow {
        val a = mutableListOf<String>()
        val modelJson: String? = savedManager.getModelAsJson()
        val type: Type? = savedManager.getModelType()
        val modelList = savedManager.getJsonToModel(modelJson, type)


        countryCode.forEach { code ->
            a.add(modelList?.firstOrNull { it.code == code }?.code ?: "")
        }
        emit(a)
    }

}