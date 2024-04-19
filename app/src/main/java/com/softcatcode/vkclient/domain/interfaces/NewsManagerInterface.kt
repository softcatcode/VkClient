package com.softcatcode.vkclient.domain.interfaces

import com.softcatcode.vkclient.domain.entities.PostData

interface NewsManagerInterface {
    suspend fun loadRecommendations(): List<PostData>

    suspend fun changeLikeStatus(post: PostData)
}