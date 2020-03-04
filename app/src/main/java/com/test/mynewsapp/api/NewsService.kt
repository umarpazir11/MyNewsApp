package com.test.mynewsapp.api

import com.test.mynewsapp.ui.data.model.HeadLineNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * News REST API access points
 */
interface NewsService {

    companion object {
        const val ENDPOINT = "https://newsapi.org/v2/"
    }

    @GET("top-headlines")
    suspend fun getHeadLines(@Query("page") page: Int? = null,
                          @Query("pageSize") pageSize: Int? = null,
                          @Query("language") language: String? = null,
                             @Query("apiKey") apiKey: String? = null): Response<HeadLineNews>
}