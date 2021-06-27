package com.shafiq.saruul.libsignalprotocolandroid.ui.main.di

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.shafiq.saruul.libsignalprotocolandroid.di.ViewModelKey
import com.shafiq.saruul.libsignalprotocolandroid.storage.ExampleStorageHandler
import com.shafiq.saruul.libsignalprotocolandroid.storage.StorageHandler
import com.shafiq.saruul.libsignalprotocolandroid.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideGson(): Gson {
            return Gson()
        }
    }

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindStorageHandler(exampleStorageHandler: ExampleStorageHandler): StorageHandler
}
