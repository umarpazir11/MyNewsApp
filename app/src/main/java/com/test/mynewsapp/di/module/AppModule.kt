package com.test.mynewsapp.di.module

import com.test.mynewsapp.api.NewsService
import com.test.mynewsapp.di.CoroutineScropeIO
import com.test.mynewsapp.di.NewsAPI
import com.test.mynewsapp.ui.data.HeadLinesRemoteDataSource
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, CoreDataModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideLegoService(@NewsAPI okhttpClient: OkHttpClient,
                           converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, NewsService::class.java)

    @Singleton
    @Provides
    fun provideHeadLinesRemoteDataSource(legoService: NewsService)
            = HeadLinesRemoteDataSource(legoService)

//    @Singleton
//    @Provides
//    fun provideLegoThemeRemoteDataSource(legoService: NewsService)
//            = HeadLinesRemoteDataSource(legoService)

    @NewsAPI
    @Provides
    fun providePrivateOkHttpClient(
            upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder().build()
    }



    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)


    private fun createRetrofit(
            okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(NewsService.ENDPOINT)
                .client(okhttpClient)
                .addConverterFactory(converterFactory)
                .build()
    }

    private fun <T> provideService(okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory, clazz: Class<T>): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }
}
