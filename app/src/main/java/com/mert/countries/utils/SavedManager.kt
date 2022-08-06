package com.mert.countries.utils

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mert.countries.common.Constants
import com.mert.countries.data.model.Country
import java.lang.reflect.Type
import javax.inject.Inject

class SavedManager @Inject constructor(
    private val preferences: SharedPreferences,
    private val gson: Gson
) {
    private var _isOperationSucceeded = MutableLiveData(false)
    val isOperationSucceeded: LiveData<Boolean>
        get() = _isOperationSucceeded

    fun getTempCountries(): ArrayList<Country>? {
        val modelJson: String? = preferences.getString(Constants.COUNTRY_KEY, null)
        val type: Type? = object : TypeToken<ArrayList<Country>>() {}.type

        return gson.fromJson(modelJson, type)
    }

    fun removeTempCountry(country: Country) {
        val countries = getTempCountries() ?: arrayListOf()

        val requiredCountry = countries.firstOrNull { it.code == country.code }

        if (requiredCountry == null) {
            _isOperationSucceeded.postValue(false)
            return
        } else {
            countries.remove(requiredCountry)
            val modelToJson: String? = gson.toJson(countries)
            with(preferences.edit()) {
                putString(Constants.COUNTRY_KEY, modelToJson)
                apply()
            }
            _isOperationSucceeded.postValue(true)
        }
    }

    fun countryTempInSaved(country: Country): Boolean {
        val countries = getTempCountries() ?: arrayListOf()

        for (item in countries) {
            if (country.code == item.code) {
                return true
            }
        }
        return false
    }

    fun getModelAsJson(): String? {
        return preferences.getString(Constants.COUNTRY_KEY, null)
    }

    fun getModelType(): Type? {
        return object : TypeToken<ArrayList<Country>>() {}.type
    }

    fun getJsonToModel(modelJson: String?, type: Type?): ArrayList<Country>? {
        return gson.fromJson<ArrayList<Country>?>(modelJson, type)
    }

    fun getModelToJson(modelList: ArrayList<Country>?): String? {
        return gson.toJson(modelList)
    }
}