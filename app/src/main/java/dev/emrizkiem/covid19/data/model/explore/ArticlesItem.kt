package dev.emrizkiem.covid19.data.model.explore

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ArticlesItem (
    @Expose @SerializedName("source") val source: Source? = null,
    @Expose @SerializedName("author") val author: String? = null,
    @Expose @SerializedName("title") val title: String? = null,
    @Expose @SerializedName("description") val description: String? = null,
    @Expose @SerializedName("url") val url: String? = null,
    @Expose @SerializedName("urlToImage") val urlToImage: String? = null,
    @Expose @SerializedName("publishedAt") val publishedAt: String? = null,
    @Expose @SerializedName("content") val content: String? = null
)