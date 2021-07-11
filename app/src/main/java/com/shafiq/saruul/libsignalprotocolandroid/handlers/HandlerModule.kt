package com.shafiq.saruul.libsignalprotocolandroid.handlers

import android.content.Context
import com.google.gson.Gson
import com.shafiq.saruul.libsignalprotocolandroid.handlers.encryption.*
import com.shafiq.saruul.libsignalprotocolandroid.handlers.storage.ExampleStorageHandler
import com.shafiq.saruul.libsignalprotocolandroid.handlers.storage.StorageHandler
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.whispersystems.libsignal.state.SignedPreKeyStore
import javax.inject.Named

@Module
abstract class HandlerModule {

    @Module
    companion object {

        const val HOME_DIRECTORY = "HOME_DIRECTORY"

        @JvmStatic
        @Named(HOME_DIRECTORY)
        @Provides
        fun provideHomeDirectory(context: Context): String {
            return context.filesDir.absolutePath
        }

        @JvmStatic
        @Provides
        fun provideGson(): Gson {
            return Gson()
        }
    }

    @Binds
    abstract fun bindEncryptionHandler(exampleEncryptionHandler: ExampleEncryptionHandler): EncryptionHandler

    @Binds
    abstract fun bindPreKeyStore(examplePreKeyStore: ExamplePreKeyStore): PreKeyStore

    @Binds
    abstract fun bindSignedPreKeyStore(exampleSignedPreKeyStore: ExampleSignedPreKeyStore): SignedPreKeyStore

    @Binds
    abstract fun bindStorageHandler(exampleStorageHandler: ExampleStorageHandler): StorageHandler
}
