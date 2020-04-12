package dev.emrizkiem.covid19.data.model.info

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SymptomsResponse (
    @Expose @SerializedName("status") val status: String? = null,
    @Expose @SerializedName("symptoms") val symptoms: List<Symptoms>? = null
)