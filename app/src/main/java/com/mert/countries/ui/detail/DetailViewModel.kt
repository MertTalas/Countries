package com.mert.countries.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mert.countries.common.Resource
import com.mert.countries.domain.usecase.GetCountryDetailByIdUseCase
import com.mert.countries.utils.SavedManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCountryDetailByIdUseCase: GetCountryDetailByIdUseCase,
    val savedManager: SavedManager
): ViewModel(){

    fun fetchCountryDetail(countryCode: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(data = getCountryDetailByIdUseCase.invoke(countryCode)))
        } catch (exception: Exception) {
            emit(Resource.error(exception.message ?: "Error Occured!", data = null))
        }
    }
}