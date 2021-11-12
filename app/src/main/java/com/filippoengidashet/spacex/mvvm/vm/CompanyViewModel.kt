package com.filippoengidashet.spacex.mvvm.vm

import androidx.lifecycle.*
import com.filippoengidashet.spacex.mvvm.model.entity.bean.CompanyData
import com.filippoengidashet.spacex.mvvm.model.entity.bean.ResultBean
import com.filippoengidashet.spacex.mvvm.model.repository.SpaceXRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Filippo 09/11/2021
 */
@HiltViewModel
class CompanyViewModel @Inject constructor(
    private val repository: SpaceXRepository,
) : ViewModel() {

    private var loaded: Boolean = false
    private val _companyData = MutableLiveData<ResultBean<CompanyData>>()
    fun getCompanyLiveData(): LiveData<ResultBean<CompanyData>> = _companyData

    fun load() {
        if(loaded) return
        loaded = true
        viewModelScope.launch {
            repository.getCompanyDataFlow().collectLatest { data ->
                _companyData.value = data
            }
        }
    }
}
