package com.pixabay.viewer.models
import com.google.gson.annotations.SerializedName

data class ImagesListResponseSchema(
    @SerializedName("hits")
    val hits: MutableList<Hit>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int
)

