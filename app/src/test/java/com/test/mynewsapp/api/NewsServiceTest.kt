package com.test.mynewsapp.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RunWith(JUnit4::class)
class NewsServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: NewsService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun requestTopHeadLines() {
        runBlocking {
            enqueueResponse("headlinesarticles.json")
            val resultResponse = service.getHeadLines().body()

            val request = mockWebServer.takeRequest()
            Assert.assertNotNull(resultResponse)
            Assert.assertThat(request.path, CoreMatchers.`is`("/top-headlines"))
        }
    }

    @Test
    fun getTopHeadLinesResponse() {
        runBlocking {
            enqueueResponse("headlinesarticles.json")
            val resultResponse = service.getHeadLines().body()
            val legoSets = resultResponse!!.articles

            Assert.assertThat(resultResponse.totalResults, CoreMatchers.`is`(38))
            Assert.assertThat(legoSets.size, CoreMatchers.`is`(20))
        }
    }

    @Test
    fun getTopHeadLinesItem() {
        runBlocking {
            enqueueResponse("headlinesarticles.json")
            val resultResponse = service.getHeadLines().body()
            val aticles = resultResponse!!.articles

            val article = aticles[0]
            Assert.assertThat(article.author, CoreMatchers.`is`("Brie Stimson"))
            Assert.assertThat(article.title, CoreMatchers.`is`("US military says it " +
                    "conducted airstrike against Taliban forces, first since peace deal - Fox News"))
            Assert.assertThat(article.description, CoreMatchers.`is`("The U.S. military " +
                    "said early Wednesday it had conducted an airstrike against Taliban forces in " +
                    "Afghanistan, the first such attack since a historic peace deal was signed " +
                    "with the militant group Saturday."))
            Assert.assertThat(
                article.url,
                CoreMatchers.`is`("https://www.foxnews.com/world/in-first-attack-since-" +
                        "peace-deal-us-military-says-it-conducted-airstrike-against-taliban-forces")
            )
            Assert.assertThat(
                article.urlToImage,
                CoreMatchers.`is`("https://cf-images.us-east-1.prod.boltdns.net/v1/static" +
                        "/694940094001/f082ae45-9e76-4609-b3d4-0b968823a881/c8af861d-5231-42f4-" +
                        "9e14-c7963f4c78f0/1280x720/match/image.jpg")
            )
            Assert.assertThat(article.publishedAt, CoreMatchers.`is`("2020-03-04T09:19:05Z"))
            Assert.assertThat(article.content, CoreMatchers.`is`("The U.S. military said " +
                    "early Wednesday it had conducted an airstrike against Taliban forces in Afghanistan"))
        }
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody(
            source.readString(Charsets.UTF_8))
        )
    }
}