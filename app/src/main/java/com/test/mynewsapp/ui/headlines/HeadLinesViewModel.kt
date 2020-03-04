package com.test.mynewsapp.ui.headlines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.mynewsapp.di.CoroutineScropeIO
import com.test.mynewsapp.ui.data.HeadLineNews
import com.test.mynewsapp.ui.data.HeadLinesRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.test.mynewsapp.api.Result

class HeadLinesViewModel @Inject constructor(repository: HeadLinesRemoteDataSource,
                                             @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope) : ViewModel() {
    val observableHeadLines: MutableLiveData<Result<HeadLineNews>> = MutableLiveData()

    init {
        ioCoroutineScope.launch {
            val headLines = repository.fetchData()
            observableHeadLines.postValue(headLines)
        }
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }
}
