package com.test.mynewsapp.ui.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.test.mynewsapp.ui.data.model.Article
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class HeadLinesRepository @Inject constructor(private val legoSetRemoteDataSource: HeadLinesRemoteDataSource) {

    fun observePagedSets(coroutineScope: CoroutineScope) = observeRemotePagedSets(coroutineScope)

    private fun observeRemotePagedSets(ioCoroutineScope: CoroutineScope)
            : LiveData<PagedList<Article>> {
        val dataSourceFactory =
            HeadLinePageDataSourceFactory(legoSetRemoteDataSource, ioCoroutineScope)

        return LivePagedListBuilder(
            dataSourceFactory,
            HeadLinePageDataSourceFactory.pagedListConfig()
        ).build()
    }
}
