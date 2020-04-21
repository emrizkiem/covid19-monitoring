package dev.emrizkiem.covid19.data.model.global

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data (
    @Expose @SerializedName("value") val value: Int? = null
)