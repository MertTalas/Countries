package com.mert.countries.utils

import android.content.SharedPreferences
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
    private var savedLiveData: MutableLiveData<Boolean>? = null

    fun getCountries(): ArrayList<Country>? {
        val jsonString: String? = preferences.getString(Constants.SHARED_PREFERENCES_KEY, null)
        val type: Type? = object : TypeToken<ArrayList<Country>>() {}.type
        return gson.fromJson(jsonString, type)
    }

    fun addCountry(country: Country) {
        val countries = getCountries() ?: arrayListOf()
        countries.add(country)
        saveModelAsJson(countries)
        savedLiveData?.postValue(true)
    }

    fun removeCountry(country: Country) {
        val countries = getCountries() ?: arrayListOf()
        val requiredCountry = countries.firstOrNull { it.code == country.code }
        if (requiredCountry != null) {
            countries.remove(requiredCountry)
            saveModelAsJson(countries)
            savedLiveData?.postValue(true)
        }
    }

    private fun saveModelAsJson(countries: java.util.ArrayList<Country>) {
        val editor = preferences.edit()
        val jsonString: String? = gson.toJson(countries)
        editor.putString(Constants.SHARED_PREFERENCES_KEY, jsonString)
        editor.apply()
    }

    fun isCountrySaved(country: Country): Boolean {
        val countries = getCountries() ?: arrayListOf()
        val savedCountry = countries.firstOrNull { it.code == country.code }
        return savedCountry != null
    }

    fun getSavedLiveData(): MutableLiveData<Boolean>? {
        if (savedLiveData == null) savedLiveData = MutableLiveData<Boolean>()
        return savedLiveData
    }
}