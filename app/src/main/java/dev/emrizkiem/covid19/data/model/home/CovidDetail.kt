package dev.emrizkiem.covid19.data.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CovidDetail (
    @Expose @SerializedName("fid") val fid: Int? = null,
    @Expose @SerializedName("kodeProvi") val kodeProvi: Int? = null,
    @Expose @SerializedName("provinsi") val province: String? = null,
    @Expose @SerializedName("kasusPosi") val confirmed: Int? = null,
    @Expose @SerializedName("kasusSemb") val recovered: Int? = null,
    @Expose @SerializedName("kasusMeni") val death: Int? = null
)