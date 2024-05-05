package com.softcatcode.vkclient.di

import androidx.lifecycle.ViewModel
import com.softcatcode.vkclient.presentation.favourites.FavouritesViewModel
import com.softcatcode.vkclient.presentation.home.auth.AuthViewModel
import com.softcatcode.vkclient.presentation.home.comments.CommentsViewModel
import com.softcatcode.vkclient.presentation.home.news.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    @Binds
    fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel

    @IntoMap
    @ViewModelKey(FavouritesViewModel::class)
    @Binds
    fun bindFavouritesViewModel(viewModel: FavouritesViewModel): ViewModel

    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    @Binds
    fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel
}
