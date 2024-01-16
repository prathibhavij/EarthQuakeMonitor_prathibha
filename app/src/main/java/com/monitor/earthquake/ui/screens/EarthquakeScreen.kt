package com.monitor.earthquake.ui.screens

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.progressSemantics
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.monitor.earthquake.model.EarthquakeResponse
import com.monitor.earthquake.model.Feature
import com.monitor.earthquake.model.NetworkResult
import com.monitor.earthquake.model.Properties
import com.monitor.earthquake.viewmodels.EarthquakeViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun EarthquakeApp(uiState: State<NetworkResult<EarthquakeResponse>?>,
                  navHostController: NavHostController) {

    Scaffold {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when (val uiStateValue = uiState.value) {
                is NetworkResult.Success -> {
                    uiStateValue.data?.let { it1 ->
                        SummaryScreen(
                            it1.features,
                            modifier = Modifier.fillMaxSize(),
                            navHostController
                        )
                    }
                }

                is NetworkResult.Error -> {
                    ErrorScreen(uiStateValue.message)
                }

                is NetworkResult.Loading -> {
                    LoadingScreen()
                }

                else -> { }
            }
        }
    }

}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun SummaryScreen(list: List<Feature>, modifier: Modifier = Modifier,
                  navHostController: NavHostController) {
    LazyColumn(
        Modifier.padding(16.dp)
    ) {
        itemsIndexed(list) { index, item ->
            SummaryRow(item,navHostController)
            if (index < list.lastIndex)
                Divider(
                    color = Color.Black.copy(alpha = 0.3f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
        }
    }
}

@Composable
fun SummaryRow(item: Feature,  navHostController: NavHostController) {

    val magnitude = item.properties.mag
    val background = if (magnitude in 0.0..0.9) Color.Green
    else if (magnitude in 9.0..9.9) Color.Red else Color.Yellow

    Card(
        modifier = Modifier.padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = background,
        ),
    ) {
        Row(Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.properties.mag.toString())
                Text(text = item.properties.place)
            }
            IconButton(onClick = {  navHostController.navigate("Detail")  }) {
                Icon(Icons.Rounded.KeyboardArrowRight, contentDescription = "More Details")
            }
        }

    }

}


/**
 * Display the loading message
 */
@Composable
fun LoadingScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.then(
                Modifier
                    .progressSemantics()
                    .size(32.dp)
                    .testTag("Progress Indicator")
            )
        )
    }
}

/**
 * Screen displaying error message
 */
@Composable
fun ErrorScreen(error: String? , modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (error != null) {
            Text(text = error, modifier = Modifier.padding(16.dp))
        }
    }
}
