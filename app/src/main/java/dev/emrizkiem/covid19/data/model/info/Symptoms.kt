package dev.emrizkiem.covid19.data.model.info

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Symptoms (
    @Expose @SerializedName("name") val name: String? = null,
    @Expose @SerializedName("icon") val icon: String? = null
)