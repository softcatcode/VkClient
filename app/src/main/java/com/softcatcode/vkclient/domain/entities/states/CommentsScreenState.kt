package com.softcatcode.vkclient.domain.entities.states

import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.entities.Comment

sealed class CommentsScreenState {
    data object Initial: CommentsScreenState()

    data class Comments(
        val commentList: List<Comment>,
        val post: PostData
    ): CommentsScreenState()
}