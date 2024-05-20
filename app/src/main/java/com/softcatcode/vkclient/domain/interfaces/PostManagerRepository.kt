package com.softcatcode.vkclient.domain.interfaces

import com.softcatcode.vkclient.domain.entities.Comment
import com.softcatcode.vkclient.domain.entities.PostData
import kotlinx.coroutines.flow.StateFlow

interface PostManagerRepository {
    suspend fun loadNext()

    suspend fun ignorePost(id: Long)

    suspend fun changeLikeStatus(post: PostData)

    fun getPosts(): StateFlow<List<PostData>>
}