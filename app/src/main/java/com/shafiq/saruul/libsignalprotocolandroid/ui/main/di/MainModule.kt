package com.shafiq.saruul.libsignalprotocolandroid.ui.main.di

import androidx.lifecycle.ViewModel
import com.shafiq.saruul.libsignalprotocolandroid.di.ViewModelKey
import com.shafiq.saruul.libsignalprotocolandroid.handlers.HandlerModule
import com.shafiq.saruul.libsignalprotocolandroid.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [HandlerModule::class])
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewModel: MainViewModel): ViewModel
}
