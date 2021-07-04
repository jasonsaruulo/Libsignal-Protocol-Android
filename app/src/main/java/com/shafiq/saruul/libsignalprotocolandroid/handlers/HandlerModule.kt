package com.shafiq.saruul.libsignalprotocolandroid.handlers

import com.google.gson.Gson
import com.shafiq.saruul.libsignalprotocolandroid.handlers.encryption.ExamplePreKeyStore
import com.shafiq.saruul.libsignalprotocolandroid.handlers.encryption.ExampleSignedPreKeyStore
import com.shafiq.saruul.libsignalprotocolandroid.handlers.encryption.PreKeyStore
import com.shafiq.saruul.libsignalprotocolandroid.handlers.storage.ExampleStorageHandler
import com.shafiq.saruul.libsignalprotocolandroid.handlers.storage.StorageHandler
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.whispersystems.libsignal.state.SignedPreKeyStore

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
    abstract fun bindSignedPreKeyStore(exampleSignedPreKeyStore: ExampleSignedPreKeyStore): SignedPreKeyStore

    @Binds
    abstract fun bindStorageHandler(exampleStorageHandler: ExampleStorageHandler): StorageHandler
}
