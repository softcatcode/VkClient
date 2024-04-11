package com.softcatcode.vkclient.data.network

import com.softcatcode.vkclient.data.model.NewsFeedResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("newsfeed.getRecomended?v=5.131")
    suspend fun getRecommendations(
        @Query("access_token") token: String
    ): NewsFeedResponseDto
}