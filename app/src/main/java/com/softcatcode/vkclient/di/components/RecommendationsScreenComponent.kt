package com.softcatcode.vkclient.di.components

import com.softcatcode.vkclient.di.ViewModelFactory
import com.softcatcode.vkclient.di.modules.RecommendationsScreenModule
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = [RecommendationsScreenModule::class])
interface RecommendationsScreenComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(): RecommendationsScreenComponent
    }

}