package com.test.mynewsapp.di.module

import com.test.mynewsapp.ui.headlines.HeadLinesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun headLinesFragment(): HeadLinesFragment


}
