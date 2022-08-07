package com.mert.countries.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mert.countries.common.Resource
import com.mert.countries.domain.usecase.GetCountriesUseCase
import com.mert.countries.utils.SavedManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    val savedManager: SavedManager
) : ViewModel() {

    fun fetchCountries() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(data = getCountriesUseCase.invoke()))
        } catch (exception: Exception) {
            emit(Resource.error(exception.message ?: "Something went wrong!", data = null))
        }
    }
}