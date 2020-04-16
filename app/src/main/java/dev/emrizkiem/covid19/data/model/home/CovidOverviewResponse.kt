package dev.emrizkiem.covid19.data.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CovidOverviewResponse (
    @Expose @SerializedName("confirmed") val confirmed: CovidOverview? = null,
    @Expose @SerializedName("recovered") val recovered: CovidOverview? = null,
    @Expose @SerializedName("deaths") val deaths: CovidOverview? = null
)