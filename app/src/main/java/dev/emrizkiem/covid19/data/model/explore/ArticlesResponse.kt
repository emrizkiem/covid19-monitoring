package dev.emrizkiem.covid19.data.model.explore

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ArticlesResponse (
    @Expose @SerializedName("status") val status: String? = null,
    @Expose @SerializedName("totalResults") val totalResults: Int? = null,
    @Expose @SerializedName("articles") val articles: List<ArticlesItem>? = null
)