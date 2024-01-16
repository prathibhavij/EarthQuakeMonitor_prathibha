package com.monitor.earthquake.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monitor.earthquake.data.repository.EarthquakeRepository
import com.monitor.earthquake.model.EarthquakeResponse
import com.monitor.earthquake.model.Feature
import com.monitor.earthquake.model.NetworkResult
import kotlinx.coroutines.launch

class EarthquakeViewModel(private val repository: EarthquakeRepository): ViewModel() {


    private val _response: MutableLiveData<NetworkResult<EarthquakeResponse>> = MutableLiveData()
    val response: LiveData<NetworkResult<EarthquakeResponse>> = _response

    private val _detail: MutableLiveData<NetworkResult<Feature>> = MutableLiveData()
    val detail: LiveData<NetworkResult<Feature>> = _detail

    fun getSummaryResponse() {
        viewModelScope.launch {
            repository.getSummary().collect { values ->
                _response.value = values
            }
        }

    }

    fun getDetail(url:String) {
        viewModelScope.launch {
            repository.getDetail(url).collect { values ->
                _detail.value = values
            }
        }

    }
}