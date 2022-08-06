package com.mert.countries.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mert.countries.domain.uimodel.CountryUI
import com.mert.countries.domain.usecase.GetCountriesUseCase
import com.mert.countries.domain.usecase.local.CheckIfSavedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val checkIfSavedUseCase: CheckIfSavedUseCase,
) : ViewModel() {

    val countryList = MutableLiveData<ArrayList<CountryUI>?>()
    val isCountrySaved = MutableLiveData<Boolean>()

    fun initVM() {
        fetchCountries()
    }

    private fun fetchCountries() = viewModelScope.launch(Dispatchers.IO) {
        getCountriesUseCase.invoke().collect {
            countryList.postValue(it as ArrayList<CountryUI>?)
        }
    }

    fun test() = viewModelScope.launch(Dispatchers.IO) {
        getCountriesUseCase.invoke().flatMapMerge { countryList ->
            checkIfSavedUseCase.invoke(
                CheckIfSavedUseCase.Params(
                    countryList?.map { countryUI -> countryUI.countryId ?: "" } ?: emptyList()
                )
            )
        }.collect{
            Timber.d("Kayıtlı liste denemesi $it")
        }

    }

//    fun fetchIfCountrySaved(countryCode: String?) = viewModelScope.launch(Dispatchers.IO) {
//        checkIfSavedUseCase.invoke(
//            CheckIfSavedUseCase.Params(
//                countryCode ?: ""
//            )
//        ).collect {
//            isCountrySaved.postValue(it)
//        }
//    }
}