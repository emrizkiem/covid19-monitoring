package dev.emrizkiem.covid19.data.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CovidOverviewResponse (
    @Expose @SerializedName("confirmed") val confirmed: List<CovidOverviewItem>?= null,
    @Expose @SerializedName("recovered") val recovered: List<CovidOverviewItem>?= null,
    @Expose @SerializedName("deaths") val deaths: List<CovidOverviewItem>?= null
)