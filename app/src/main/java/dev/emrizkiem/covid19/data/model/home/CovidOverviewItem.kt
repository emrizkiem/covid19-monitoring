package dev.emrizkiem.covid19.data.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CovidOverviewItem (
    @Expose @SerializedName("value") val value: Int? = null,
    @Expose @SerializedName("detail") val detail: String? = null
)