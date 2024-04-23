package com.softcatcode.vkclient.domain.interfaces

import com.softcatcode.vkclient.domain.entities.AuthState
import com.softcatcode.vkclient.domain.entities.Comment
import com.softcatcode.vkclient.domain.entities.PostData
import com.vk.api.sdk.auth.VKAuthenticationResult
import kotlinx.coroutines.flow.Flow

interface NewsManagerInterface {
    suspend fun loadNext()

    suspend fun ignorePost(id: Long)

    suspend fun changeLikeStatus(post: PostData)

    suspend fun checkAuthResult()

    fun getComments(post: PostData): Flow<List<Comment>>

    fun getRecommendations(): Flow<List<PostData>>

    fun getAuthStateFlow(): Flow<AuthState>
}