package com.monitor.earthquake.data.repository

import com.monitor.earthquake.data.network.EarthquakeApiHelper
import com.monitor.earthquake.model.BaseApiResponse
import com.monitor.earthquake.model.EarthquakeResponse
import com.monitor.earthquake.model.Feature
import com.monitor.earthquake.model.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EarthquakeRepository(private val earthquakeApiHelper: EarthquakeApiHelper)
    : BaseApiResponse() {

    suspend fun getSummary(): Flow<NetworkResult<EarthquakeResponse>> {
        return flow<NetworkResult<EarthquakeResponse>> {
            emit(safeApiCall { earthquakeApiHelper.getSummary() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetail(url : String): Flow<NetworkResult<Feature>> {
        return flow<NetworkResult<Feature>> {
            emit(safeApiCall { earthquakeApiHelper.getDetail(url) })
        }.flowOn(Dispatchers.IO)
    }
}
