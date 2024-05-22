package com.softcatcode.vkclient.di.modules

import androidx.lifecycle.ViewModel
import com.softcatcode.vkclient.di.annotations.ViewModelKey
import com.softcatcode.vkclient.presentation.comments.CommentsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CommentsViewModelModule {
    @IntoMap
    @ViewModelKey(CommentsViewModel::class)
    @Binds
    fun bindCommentsViewModel(viewModel: CommentsViewModel): ViewModel
}