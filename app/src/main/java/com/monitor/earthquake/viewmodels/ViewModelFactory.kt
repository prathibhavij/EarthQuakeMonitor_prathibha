package com.monitor.earthquake.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.monitor.earthquake.data.network.EarthquakeApiHelper
import com.monitor.earthquake.data.repository.EarthquakeRepository

class ViewModelFactory(private val earthquakeApiHelper: EarthquakeApiHelper) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =  EarthquakeViewModel(
        EarthquakeRepository(earthquakeApiHelper)
    ) as T
}

