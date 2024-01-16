package com.monitor.earthquake.ui.screens

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.monitor.earthquake.data.network.EarthquakeApi
import com.monitor.earthquake.data.network.EarthquakeApiHelper
import com.monitor.earthquake.model.Feature
import com.monitor.earthquake.viewmodels.EarthquakeViewModel
import com.monitor.earthquake.viewmodels.ViewModelFactory

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun DetailScreen(feature: Feature) {
    val viewModel: EarthquakeViewModel =
        viewModel(factory = ViewModelFactory(EarthquakeApiHelper(EarthquakeApi.retrofitService)))
    viewModel.getDetail(feature.properties.detail)
    val uiState = viewModel.detail.observeAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = feature.properties.mag.toString())
        Text(text = feature.properties.place)
    }
}
