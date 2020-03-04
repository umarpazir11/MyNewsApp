package com.test.mynewsapp.di.module

import com.test.mynewsapp.ui.headlines.HeadLinesFragment
import com.test.mynewsapp.ui.headlinesdetails.DetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun headLinesFragment(): HeadLinesFragment

    @ContributesAndroidInjector
    abstract fun detailsFragment(): DetailsFragment


}
