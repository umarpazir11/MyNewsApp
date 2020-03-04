package com.test.mynewsapp.di.module

import com.test.mynewsapp.MainActivity
import com.test.mynewsapp.di.module.FragmentBuildersModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun mainActivity(): MainActivity
}
