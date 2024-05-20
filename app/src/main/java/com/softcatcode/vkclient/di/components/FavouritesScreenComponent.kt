package com.softcatcode.vkclient.di.components

import com.softcatcode.vkclient.di.ViewModelFactory
import com.softcatcode.vkclient.di.modules.FavouritesScreenModule
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = [FavouritesScreenModule::class])
interface FavouritesScreenComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(): FavouritesScreenComponent
    }
}