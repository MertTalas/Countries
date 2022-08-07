package com.mert.countries.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.mert.countries.common.Resource
import com.mert.countries.domain.uimodel.CountryDetailUI
import com.mert.countries.domain.uimodel.CountryUI
import com.mert.countries.domain.usecase.GetCountryDetailByIdUseCase
import com.mert.countries.utils.SavedManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCountryDetailByIdUseCase: GetCountryDetailByIdUseCase,
): ViewModel(){

//    val countryDetail = MutableLiveData<CountryDetailUI>()
//
//    fun initVM() {
//        countryDetail.value?.countryCode?.let { fetchCountryDetail(it) }
//    }
//
//    private fun fetchCountryDetail(countryCode: String) = viewModelScope.launch(Dispatchers.IO) {
//        getCountryDetailByIdUseCase.invoke(countryCode).collect() {
//            countryDetail.postValue(it)
//        }
//    }
}