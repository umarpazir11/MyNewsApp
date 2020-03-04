package com.test.mynewsapp.ui.headlinesdetails

import androidx.lifecycle.ViewModel
import com.test.mynewsapp.di.CoroutineScropeIO
import com.test.mynewsapp.ui.data.HeadLinesRepository
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class DetailsViewModel @Inject constructor(repository: HeadLinesRepository,
                                           @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope
) : ViewModel() {
    // TODO: Bring data from details API (if there is any) and wire up with DetailsFragment
}
