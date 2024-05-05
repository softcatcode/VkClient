package com.softcatcode.vkclient.presentation.favourites

import com.softcatcode.vkclient.domain.useCase.ChangeLikeStatusUseCase
import com.softcatcode.vkclient.domain.useCase.GetFavouritesUseCase
import com.softcatcode.vkclient.domain.useCase.GetRecommendationsUseCase
import com.softcatcode.vkclient.domain.useCase.IgnorePostUseCase
import com.softcatcode.vkclient.domain.useCase.LoadNextFavouritesUseCase
import com.softcatcode.vkclient.domain.useCase.LoadNextPostsUseCase
import com.softcatcode.vkclient.domain.useCase.RemoveFromFavouritesUseCase
import com.softcatcode.vkclient.presentation.home.news.NewsViewModel
import javax.inject.Inject

class FavouritesViewModel @Inject constructor(
    private val loadNextFavouritesUseCase: LoadNextFavouritesUseCase,
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val removeFromFavouritesUseCase: RemoveFromFavouritesUseCase,
    getRecommendationsUseCase: GetRecommendationsUseCase,
    loadNextPostsUseCase: LoadNextPostsUseCase,
    ignorePostUseCase: IgnorePostUseCase,
    changeLikeStatusUseCase: ChangeLikeStatusUseCase
): NewsViewModel(getRecommendationsUseCase, loadNextPostsUseCase, ignorePostUseCase, changeLikeStatusUseCase) {

    override fun invokeGetPostsUseCase() = getFavouritesUseCase()
    override suspend fun invokeLoadNextUseCase() = loadNextFavouritesUseCase()
    override suspend fun invokeRemovePostUseCase(id: Long) = removeFromFavouritesUseCase(id)
}