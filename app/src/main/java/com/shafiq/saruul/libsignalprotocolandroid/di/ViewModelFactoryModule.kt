package com.shafiq.saruul.libsignalprotocolandroid.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(
        factory: LibsignalProtocolAndroidViewModelFactory
    ): ViewModelProvider.Factory
}
