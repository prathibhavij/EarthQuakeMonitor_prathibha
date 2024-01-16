package com.monitor.earthquake.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.monitor.earthquake.model.NetworkResult
import com.monitor.earthquake.utils.Utils
import com.monitor.earthquake.viewmodels.EarthquakeViewModel
import com.monitor.earthquake.viewmodels.ViewModelFactory

@RequiresApi(Build.VERSION_CODES.O)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun DetailScreen(viewModel: EarthquakeViewModel) {
     val feature = viewModel.data.value
    feature?.properties?.detail?.let { viewModel.getDetail(it) }
    val uiState = viewModel.detail.observeAsState()
    val featureDetail = uiState.value?.data
    when (val uiStateValue = uiState.value) {

        is NetworkResult.Success -> {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = featureDetail?.properties?.mag.toString())
                featureDetail?.properties?.place?.let { Text(text = it) }
                featureDetail?.properties?.let { Text(Utils.getTimeInMillisToDate(it.time)) }
            }
        }

        else -> {}
    }


}
