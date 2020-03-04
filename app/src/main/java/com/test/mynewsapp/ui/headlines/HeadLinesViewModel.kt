package com.test.mynewsapp.ui.headlines

import androidx.lifecycle.ViewModel
import com.test.mynewsapp.di.CoroutineScropeIO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject
import com.test.mynewsapp.ui.data.HeadLinesRepository

class HeadLinesViewModel @Inject constructor(repository: HeadLinesRepository,
                                             @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope) : ViewModel() {

    val legoSets by lazy {
        repository.observePagedSets(ioCoroutineScope)
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }
}
