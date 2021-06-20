package com.shafiq.saruul.libsignalprotocolandroid.di

import com.shafiq.saruul.libsignalprotocolandroid.ui.main.di.MainModule
import com.shafiq.saruul.libsignalprotocolandroid.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LibsignalProtocolAndroidModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivityInjector(): MainActivity
}
