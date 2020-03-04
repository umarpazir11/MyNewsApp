package com.test.mynewsapp.ui.data.model


import com.google.gson.annotations.SerializedName

data class HeadLineNews(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)