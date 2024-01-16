package com.monitor.earthquake.data.network

/**
 * Helper class to handle the data from Api and provides the result to the repository
 */
class EarthquakeApiHelper(private val earthquakeApiService: EarthquakeApiService){
    suspend fun getSummary() = earthquakeApiService.getSummary()

    suspend fun getDetail(url: String) = earthquakeApiService.getDetail(url)
}