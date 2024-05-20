package com.softcatcode.vkclient.di.modules

import androidx.lifecycle.ViewModel
import com.softcatcode.vkclient.data.implementations.FavouritesRepository
import com.softcatcode.vkclient.di.annotations.ViewModelKey
import com.softcatcode.vkclient.domain.interfaces.PostManagerRepository
import com.softcatcode.vkclient.presentation.home.news.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface FavouritesScreenModule {
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    @Binds
    fun bindFavouritesViewModel(viewModel: NewsViewModel): ViewModel

    @Singleton
    @Binds
    fun bindPostRepository(impl: FavouritesRepository): PostManagerRepository
}