package dev.emrizkiem.covid19.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CovidOverviewItem (
    @Expose @SerializedName("value") val value: Int? = 0,
    @Expose @SerializedName("detail") val detail: String? = null
)