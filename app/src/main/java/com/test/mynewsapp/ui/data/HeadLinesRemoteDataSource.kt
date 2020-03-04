package com.test.mynewsapp.ui.data


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.mynewsapp.api.BaseDataSource
import com.test.mynewsapp.api.NewsService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Works with the Lego API to get data.
 */
class HeadLinesRemoteDataSource @Inject constructor(private val service: NewsService) : BaseDataSource() {

    suspend fun fetchData() = getResult { service.getHeadLines(1,21,"us", "208d5d598eff49479f979701bf01226a") }

}
