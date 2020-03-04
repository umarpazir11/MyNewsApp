package com.test.mynewsapp.ui.data

import com.test.mynewsapp.BuildConfig
import com.test.mynewsapp.api.BaseDataSource
import com.test.mynewsapp.api.NewsService
import javax.inject.Inject

/**
 * Works with the News API to get data.
 */
class HeadLinesRemoteDataSource @Inject constructor(private val service: NewsService) : BaseDataSource() {

    suspend fun fetchData() = getResult { service.getHeadLines(1,21,"us", BuildConfig.NEWS_API_KEY) }

}
