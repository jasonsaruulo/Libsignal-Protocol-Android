package com.shafiq.saruul.libsignalprotocolandroid.handlers

import com.google.gson.Gson
import com.shafiq.saruul.libsignalprotocolandroid.handlers.encryption.ExamplePreKeyStore
import com.shafiq.saruul.libsignalprotocolandroid.handlers.encryption.PreKeyStore
import com.shafiq.saruul.libsignalprotocolandroid.handlers.storage.ExampleStorageHandler
import com.shafiq.saruul.libsignalprotocolandroid.handlers.storage.StorageHandler
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module()
abstract class HandlerModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideGson(): Gson {
            return Gson()
        }
    }

    @Binds
    abstract fun bindPreKeyStore(examplePreKeyStore: ExamplePreKeyStore): PreKeyStore

    @Binds
    abstract fun bindStorageHandler(exampleStorageHandler: ExampleStorageHandler): StorageHandler
}
