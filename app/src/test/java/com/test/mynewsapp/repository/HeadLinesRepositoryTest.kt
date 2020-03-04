package com.test.mynewsapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.mynewsapp.api.NewsService
import com.test.mynewsapp.ui.data.HeadLinesRemoteDataSource
import com.test.mynewsapp.ui.data.HeadLinesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class HeadLinesRepositoryTest {
    private lateinit var repository: HeadLinesRepository
    private val service = mock(NewsService::class.java)
    private val remoteDataSource = HeadLinesRemoteDataSource(service)
    private val mockRemoteDataSource = spy(remoteDataSource)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Before
    fun init() {
        repository = HeadLinesRepository(remoteDataSource)
    }

    @Test
    fun loadHeadLinesFromNetwork() {
        runBlocking { repository.observePagedSets(coroutineScope = coroutineScope)
        }
    }

}