package com.test.mynewsapp.ui.data.model


import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String
){
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDuration(){
//        var date = LocalDateTime.of(publishedAt)
//        var period = Period.between(publishedAt, LocalDateTime.now())
    }
}