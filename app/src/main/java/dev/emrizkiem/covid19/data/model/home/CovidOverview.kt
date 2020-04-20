package dev.emrizkiem.covid19.data.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CovidOverview (
    @Expose @SerializedName("meninggal") val death: Int? = null,
    @Expose @SerializedName("sembuh") val recovered: Int? = null,
    @Expose @SerializedName("jumlahKasus") val confirmed: Int? = null
)