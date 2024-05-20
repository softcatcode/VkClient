package com.softcatcode.vkclient.domain.interfaces

import com.softcatcode.vkclient.domain.entities.Comment
import com.softcatcode.vkclient.domain.entities.PostData
import kotlinx.coroutines.flow.StateFlow

interface CommentsRepository {
    fun getComments(post: PostData): StateFlow<List<Comment>>
}