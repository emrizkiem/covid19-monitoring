package dev.emrizkiem.covid19.data.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CovidOverview (
    @Expose @SerializedName("value") val value: Int = 0,
    @Expose @SerializedName("detail") val detail: List<CovidDetail>? = null
)