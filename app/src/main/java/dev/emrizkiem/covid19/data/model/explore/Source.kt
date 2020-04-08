package dev.emrizkiem.covid19.data.model.explore

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Source (
    @Expose @SerializedName("id") val id: Any? = null,
    @Expose @SerializedName("name") val name: String? = null
)