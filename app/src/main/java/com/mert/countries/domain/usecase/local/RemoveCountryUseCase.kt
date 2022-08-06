package com.mert.countries.domain.usecase.local

import android.content.SharedPreferences
import com.mert.countries.common.Constants
import com.mert.countries.data.model.Country
import com.mert.countries.utils.SavedManager
import kotlinx.coroutines.flow.flow
import java.lang.reflect.Type
import javax.inject.Inject

class RemoveCountryUseCase @Inject constructor(
    private val savedManager: SavedManager,
    private val preferences: SharedPreferences
) {
    data class Params(
        val country: Country
    )

    suspend operator fun invoke(params: Params) = removeCountry(params.country)

    private suspend fun removeCountry(country: Country) = flow {
        val modelJson: String? = savedManager.getModelAsJson()
        val type: Type? = savedManager.getModelType()
        val modelList = savedManager.getJsonToModel(modelJson, type)

        val requiredCountry = modelList?.firstOrNull { it.code == country.code }

        if (requiredCountry == null) {
            emit(false)
        } else {
            modelList.remove(requiredCountry)
            val modelToJson: String? = savedManager.getModelToJson(modelList)
            with(preferences.edit()) {
                putString(Constants.COUNTRY_KEY, modelToJson)
                apply()
            }
            emit(true)
        }
    }
}