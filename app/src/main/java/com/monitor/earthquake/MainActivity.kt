package com.monitor.earthquake

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.monitor.earthquake.data.network.EarthquakeApi
import com.monitor.earthquake.data.network.EarthquakeApiHelper
import com.monitor.earthquake.ui.screens.DetailScreen
import com.monitor.earthquake.ui.screens.EarthquakeApp
import com.monitor.earthquake.ui.theme.EarthQuakeMonitorTheme
import com.monitor.earthquake.viewmodels.EarthquakeViewModel
import com.monitor.earthquake.viewmodels.ViewModelFactory

class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EarthQuakeMonitorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeNavigation()
                }
            }
        }
    }
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun ComposeNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "List") {
        composable("List") {
            val viewModel: EarthquakeViewModel =
                viewModel(factory = ViewModelFactory(EarthquakeApiHelper(EarthquakeApi.retrofitService)))
            viewModel.getSummaryResponse()
            val uiState = viewModel.response.observeAsState()
            EarthquakeApp(uiState,navController)
        }
        composable("Detail") {
          // DetailScreen()
        }
    }

}