package com.softcatcode.vkclient.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun getViewModelFactory(): ViewModelFactory

    fun getCommentsScreenComponentFactory(): CommentsScreenComponent.Factory

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application,
        ): ApplicationComponent
    }
}
