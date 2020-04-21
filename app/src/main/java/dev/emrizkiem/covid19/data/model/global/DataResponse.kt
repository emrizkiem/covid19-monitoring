package dev.emrizkiem.covid19.data.model.global

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataResponse (
    @Expose @SerializedName("confirmed") val confirmed: Data? = null,
    @Expose @SerializedName("deaths") val deaths: Data? = null,
    @Expose @SerializedName("recovered") val recovered: Data? = null,
    @Expose @SerializedName("lastUpdate") val lastUpdate: String? = null
)