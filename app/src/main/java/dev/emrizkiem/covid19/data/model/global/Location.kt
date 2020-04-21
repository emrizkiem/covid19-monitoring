package dev.emrizkiem.covid19.data.model.global

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Location (
    @Expose @SerializedName("provinceState") val provinceState: String? = null,
    @Expose @SerializedName("countryRegion") var countryRegion: String? = null,
    @Expose @SerializedName("lastUpdate") val lastUpdate: Long? = null,
    var readableLastUpdate: String? = null,
    @Expose @SerializedName("lat") val lat: Double? = null,
    @Expose @SerializedName("long") val long: Double? = null,
    @Expose @SerializedName("confirmed") val confirmed: Int? = null,
    @Expose @SerializedName("recovered") val recovered: Int? = null,
    @Expose @SerializedName("deaths") val deaths: Int? = null
)