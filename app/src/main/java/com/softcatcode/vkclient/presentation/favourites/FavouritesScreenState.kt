package com.softcatcode.vkclient.presentation.favourites

import com.softcatcode.vkclient.domain.entities.PostData

sealed class FavouritesScreenState {
    data object Initial: FavouritesScreenState()

    data object Loading: FavouritesScreenState()

    data class Content(
        val posts: List<PostData>,
        val nextLoading: Boolean = false
    ): FavouritesScreenState()
}