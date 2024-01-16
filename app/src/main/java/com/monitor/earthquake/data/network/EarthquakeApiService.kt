package com.monitor.earthquake.data.network

import androidx.annotation.Keep
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.monitor.earthquake.model.EarthquakeResponse
import com.monitor.earthquake.model.Feature
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient.Builder
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url


private const val BASE_URL =  "https://earthquake.usgs.gov/earthquakes/"
/**
 * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .client(Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
    .baseUrl(BASE_URL)
    .build()

/**
 * Function to provide kotlinx.serialization
 */
private fun provideKotlinSerialization(): Converter.Factory {
    val contentType = "application/json".toMediaType()
    val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
    }
    return json.asConverterFactory(contentType)
}

/**
 * Retrofit service object for creating api calls
 */
@Keep
interface EarthquakeApiService {
    @GET("feed/v1.0/summary/all_hour.geojson")
    suspend fun getSummary(): Response<EarthquakeResponse>

    @GET
    suspend fun getDetail(@Url url:String): Response<Feature>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object EarthquakeApi {
    val retrofitService: EarthquakeApiService by lazy {
        retrofit.create(EarthquakeApiService::class.java)
    }
}
