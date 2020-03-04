package com.test.mynewsapp.ui.headlines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.mynewsapp.di.CoroutineScropeIO
import com.test.mynewsapp.ui.data.model.HeadLineNews
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject
import com.test.mynewsapp.api.Result
import com.test.mynewsapp.ui.data.HeadLinesRepository

class HeadLinesViewModel @Inject constructor(repository: HeadLinesRepository,
                                             @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope) : ViewModel() {
    val observableHeadLines: MutableLiveData<Result<HeadLineNews>> = MutableLiveData()
    var connectivityAvailable: Boolean = true
    val legoSets by lazy {
        repository.observePagedSets(connectivityAvailable,ioCoroutineScope)
    }
    init {

//        ioCoroutineScope.launch {
//            val headLines = repository.fetchData(1,21)
//            observableHeadLines.postValue(headLines)
//        }


    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }
}
