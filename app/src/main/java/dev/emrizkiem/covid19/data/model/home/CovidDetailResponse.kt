package dev.emrizkiem.covid19.data.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CovidDetailResponse (
    @Expose @SerializedName("data") val data: List<CovidDetail>? = null
)