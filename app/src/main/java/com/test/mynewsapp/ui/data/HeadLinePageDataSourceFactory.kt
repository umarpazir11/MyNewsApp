package com.test.mynewsapp.ui.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.test.mynewsapp.ui.data.model.Article
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class HeadLinePageDataSourceFactory @Inject constructor(
    private val dataSource: HeadLinesRemoteDataSource,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, Article>() {

    private val liveData = MutableLiveData<HeadLinePageDataSource>()

    override fun create(): DataSource<Int, Article> {
        val source = HeadLinePageDataSource(dataSource, scope)
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 21

        fun pagedListConfig() = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }

}