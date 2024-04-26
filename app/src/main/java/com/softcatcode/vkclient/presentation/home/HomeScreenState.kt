package com.softcatcode.vkclient.presentation.home

import com.softcatcode.vkclient.domain.entities.Comment
import com.softcatcode.vkclient.domain.entities.PostData

sealed class HomeScreenState {
    data object Initial: HomeScreenState()
    data class Posts(val postList: List<PostData>): HomeScreenState()
    data class Comments(
        val commentList: List<Comment>,
        val post: PostData
    ): HomeScreenState()
}