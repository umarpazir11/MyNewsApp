package com.test.mynewsapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.mynewsapp.di.factory.ViewModelFactory
import com.test.mynewsapp.di.ViewModelKey
import com.test.mynewsapp.ui.headlines.HeadLinesViewModel
import com.test.mynewsapp.ui.headlinesdetails.DetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HeadLinesViewModel::class)
    abstract fun bindHeadLinesViewModel(viewModel: HeadLinesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
