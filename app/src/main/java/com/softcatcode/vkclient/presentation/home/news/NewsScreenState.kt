package com.softcatcode.vkclient.presentation.home.news

import com.softcatcode.vkclient.domain.entities.PostData

sealed class NewsScreenState {
    data object Initial: NewsScreenState()

    data class Posts(val postList: List<PostData>): NewsScreenState()
}