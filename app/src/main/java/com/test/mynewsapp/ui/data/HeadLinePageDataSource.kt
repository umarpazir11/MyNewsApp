package com.test.mynewsapp.ui.data

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.test.mynewsapp.api.Result
import com.test.mynewsapp.ui.data.model.Article

/**
 * Data source for Head Line News pagination via paging library
 */
class HeadLinePageDataSource @Inject constructor(
        private val dataSource: HeadLinesRemoteDataSource,
        private val scope: CoroutineScope) : PageKeyedDataSource<Int, Article>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {
        fetchData(1, params.requestedLoadSize) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(page: Int, pageSize: Int, callback: (List<Article>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            val response = dataSource.fetchData(page, pageSize)
            if (response.status == Result.Status.SUCCESS) {
                val results = response.data!!.articles
                callback(results)
            } else if (response.status == Result.Status.ERROR) {
                postError(response.message!!)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        //Timber.e("An error happened: $message")
        //networkState.postValue(NetworkState.FAILED)
    }

}