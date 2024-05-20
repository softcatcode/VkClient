package com.softcatcode.vkclient.di.components

import com.softcatcode.vkclient.di.modules.CommentsViewModelModule
import com.softcatcode.vkclient.di.ViewModelFactory
import com.softcatcode.vkclient.domain.entities.PostData
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [CommentsViewModelModule::class])
interface CommentsScreenComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance post: PostData
        ): CommentsScreenComponent
    }
}