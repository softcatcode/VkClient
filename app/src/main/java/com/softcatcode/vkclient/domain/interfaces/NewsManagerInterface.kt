package com.softcatcode.vkclient.domain.interfaces

import com.softcatcode.vkclient.domain.entities.AuthState
import com.softcatcode.vkclient.domain.entities.Comment
import com.softcatcode.vkclient.domain.entities.PostData
import kotlinx.coroutines.flow.StateFlow

interface NewsManagerInterface {
    suspend fun loadNext()

    suspend fun ignorePost(id: Long)

    suspend fun changeLikeStatus(post: PostData)

    suspend fun checkAuthResult()

    suspend fun loadFavourites()

    fun getComments(post: PostData): StateFlow<List<Comment>>

    fun getRecommendations(): StateFlow<List<PostData>>

    fun getAuthStateFlow(): StateFlow<AuthState>

    fun getFavourites(): StateFlow<List<PostData>>
}