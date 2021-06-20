package com.shafiq.saruul.libsignalprotocolandroid.di

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class LibsignalProtocolAndroidApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerLibsignalProtocolAndroidComponent.factory().create(applicationContext)
    }

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: LibsignalProtocolAndroidComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): LibsignalProtocolAndroidComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerLibsignalProtocolAndroidComponent.factory().create(applicationContext)
    }
}
