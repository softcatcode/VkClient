package com.softcatcode.vkclient.di.components

import android.app.Application
import com.softcatcode.vkclient.di.annotations.ApplicationScope
import com.softcatcode.vkclient.di.modules.DataModule
import com.softcatcode.vkclient.di.ViewModelFactory
import com.softcatcode.vkclient.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [ViewModelModule::class, DataModule::class])
interface ApplicationComponent {

    fun getViewModelFactory(): ViewModelFactory

    fun getCommentsScreenComponentFactory(): CommentsScreenComponent.Factory

    fun getFavouritesScreenComponentFactory(): FavouritesScreenComponent.Factory

    fun getRecommendationsScreenComponentFactory(): RecommendationsScreenComponent.Factory

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application,
        ): ApplicationComponent
    }
}
