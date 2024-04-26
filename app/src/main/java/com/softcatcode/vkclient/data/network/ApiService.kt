package com.softcatcode.vkclient.data.network

import com.softcatcode.vkclient.data.model.LikeCountResponseDto
import com.softcatcode.vkclient.data.model.NewsFeedResponseDto
import com.sumin.vknewsclient.data.model.CommentsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("newsfeed.getRecommended?v=$VERSION")
    suspend fun getRecommendations(
        @Query("access_token") token: String
    ): NewsFeedResponseDto

    @GET("newsfeed.getRecommended?v=$VERSION")
    suspend fun getRecommendations(
        @Query("access_token") token: String,
        @Query("start_from") startFrom: String
    ): NewsFeedResponseDto

    @GET("likes.add?v=$VERSION&type=post")
    suspend fun like(
        @Query("access_token") token: String,
        @Query("owner_id") ownerId: Long,
        @Query("item_id") postId: Long
    ): LikeCountResponseDto

    @GET("likes.delete?v=$VERSION&type=post")
    suspend fun dislike(
        @Query("access_token") token: String,
        @Query("owner_id") ownerId: Long,
        @Query("item_id") postId: Long
    ): LikeCountResponseDto

    @GET("newsfeed.ignoreItem?v=$VERSION&type=wall")
    suspend fun ignorePost(
        @Query("access_token") token: String,
        @Query("owner_id") ownerId: Long,
        @Query("item_id") itemId: Long
    )

    @GET("wall.getComments?v=$VERSION&extended=1&fields=photo_100")
    suspend fun loadComments(
        @Query("access_token") token: String,
        @Query("owner_id") ownerId: Long,
        @Query("post_id") postId: Long
    ): CommentsResponseDto

    companion object {
        private const val VERSION = "5.199"
    }
}