package com.monitor.earthquake.model

import kotlinx.serialization.Serializable

@Serializable
data class EarthquakeResponse(
    val type: String,
    val features: List<Feature>,
    val bbox: List<Double>
)

@Serializable
data class Feature(
    val type: String,
    val properties: Properties,
    val geometry: Geometry,
    val id: String
)


@Serializable
data class Properties(
    val mag: Double,
    val place: String,
    val time: Long,
    val updated: Long,
    val tz: Int,
    val url: String,
    val detail: String,
    val felt: Double?,
    val cdi: Double?,
    val mmi: Double?,
    val alert: String?,
    val status: String?,
    val tsunami: Int?,
    val sig: Int?,
    val net: String?,
    val code: String?,
    val ids: String,
    val sources: String?,
    val types: String,
    val nst: Int,
    val dmin: Double,
    val rms: Double,
    val gap: Double,
    val magType: String,
    val type: String,
    val title: String
)

@Serializable
data class Geometry(
    val type: String,
    val coordinates: List<Double>
)

