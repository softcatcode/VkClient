package com.softcatcode.vkclient.presentation.home.news

import com.softcatcode.vkclient.domain.entities.PostData

sealed class NewsScreenState {
    data object Initial: NewsScreenState()

    data object Loading: NewsScreenState()

    data class Posts(val postList: List<PostData>, val nextLoading: Boolean = false): NewsScreenState()
}