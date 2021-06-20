package com.shafiq.saruul.libsignalprotocolandroid.ui.main.di

import com.shafiq.saruul.libsignalprotocolandroid.ui.main.MainFragment
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(fragment: MainFragment)
}
