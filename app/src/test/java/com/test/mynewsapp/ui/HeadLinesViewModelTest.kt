package com.test.mynewsapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.mynewsapp.ui.data.HeadLinesRepository
import com.test.mynewsapp.ui.headlines.HeadLinesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class HeadLinesViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val repository = mock(HeadLinesRepository::class.java)
    private var viewModel = HeadLinesViewModel(repository, coroutineScope)

    @Test
    fun testNull() {

        verify(repository, never()).observePagedSets(coroutineScope)
    }

}