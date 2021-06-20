package com.shafiq.saruul.libsignalprotocolandroid.di

import android.content.Context
import com.shafiq.saruul.libsignalprotocolandroid.ui.main.di.MainComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [LibsignalProtocolAndroidModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelFactoryModule::class,
        SubcomponentsModule::class]
)
interface LibsignalProtocolAndroidComponent : AndroidInjector<LibsignalProtocolAndroidApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): LibsignalProtocolAndroidComponent
    }

    fun mainComponent(): MainComponent.Factory
}

@Module(subcomponents = [MainComponent::class])
object SubcomponentsModule
