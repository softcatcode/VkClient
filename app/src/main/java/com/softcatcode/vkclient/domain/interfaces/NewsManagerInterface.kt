package com.softcatcode.vkclient.domain.interfaces

import com.softcatcode.vkclient.domain.entities.Comment
import com.softcatcode.vkclient.domain.entities.PostData

interface NewsManagerInterface {
    suspend fun loadRecommendations()

    suspend fun ignorePost(id: Long)

    suspend fun changeLikeStatus(post: PostData)

    suspend fun getComments(post: PostData): List<Comment>
}