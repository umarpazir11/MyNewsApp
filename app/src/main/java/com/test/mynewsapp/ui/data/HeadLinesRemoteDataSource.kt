package com.test.mynewsapp.ui.data

import com.test.mynewsapp.BuildConfig
import com.test.mynewsapp.api.BaseDataSource
import com.test.mynewsapp.api.NewsService
import javax.inject.Inject

/**
 * Works with the [NewsService] to get data.
 */
class HeadLinesRemoteDataSource @Inject constructor(private val service: NewsService) : BaseDataSource() {

    suspend fun fetchData(page: Int, pageSize: Int? = null)
            = getResult { service.getHeadLines(page,pageSize,"en", BuildConfig.NEWS_API_KEY) }

}
