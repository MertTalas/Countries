package com.mert.countries.domain.usecase.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.mert.countries.common.Constants
import com.mert.countries.data.model.Country
import com.mert.countries.utils.SavedManager
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddCountryUseCase @Inject constructor(
    private val savedManager: SavedManager,
    private val preferences: SharedPreferences
) {
    data class Params(
        val country: Country
    )

    suspend operator fun invoke(params: Params) = addCountry(params.country)

    private suspend fun addCountry(country: Country) = flow {
        val jsonModel = savedManager.getModelAsJson()
        val type = savedManager.getModelType()
        val modelList = savedManager.getJsonToModel(jsonModel, type)
        modelList?.add(country)

        with(preferences.edit()) {
            putString(Constants.COUNTRY_KEY, savedManager.getModelToJson(modelList))
            apply()
        }
        emit(true)
    }
}