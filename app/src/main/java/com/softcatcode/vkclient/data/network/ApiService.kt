package com.softcatcode.vkclient.data.network

import com.softcatcode.vkclient.data.model.NewsFeedResponseDto
import com.softcatcode.vkclient.data.model.TokenResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("newsfeed.getRecommended?v=$VERSION")
    suspend fun getRecommendations(
        @Query("access_token") token: String
    ): NewsFeedResponseDto

    companion object {
        private const val VERSION = "5.199"
    }
}