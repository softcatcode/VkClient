package com.softcatcode.vkclient.domain.interfaces

import com.softcatcode.vkclient.domain.entities.Comment
import com.softcatcode.vkclient.domain.entities.PostData
import kotlinx.coroutines.flow.Flow

interface NewsManagerInterface {
    suspend fun loadNext()

    suspend fun ignorePost(id: Long)

    suspend fun changeLikeStatus(post: PostData)

    fun getComments(post: PostData): Flow<List<Comment>>
}