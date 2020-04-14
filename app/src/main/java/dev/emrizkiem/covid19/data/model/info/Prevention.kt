package dev.emrizkiem.covid19.data.model.info

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Prevention (
    @Expose @SerializedName("title") val title: String? = null,
    @Expose @SerializedName("description") val description: String? = null,
    @Expose @SerializedName("image") val image: String? = null
)