package com.softcatcode.vkclient.data.network

import com.softcatcode.vkclient.data.dtoModels.FavouritesResponseDto
import com.softcatcode.vkclient.data.dtoModels.GetGroupResponse
import com.softcatcode.vkclient.data.dtoModels.LikeCountResponseDto
import com.softcatcode.vkclient.data.dtoModels.NewsFeedResponseDto
import com.softcatcode.vkclient.data.dtoModels.ReturnStatusResponseDto
import com.softcatcode.vkclient.data.dtoModels.CommentsResponseDto
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

    @GET("fave.get?v=$VERSION&item_type=post")
    suspend fun loadFavourites(
        @Query("access_token") token: String,
        @Query("offset") offset: Int,
        @Query("count") count: Int,
    ): FavouritesResponseDto

    @GET("fave.removePost?v=$VERSION")
    suspend fun removeFromFavourites(
        @Query("access_token") token: String,
        @Query("owner_id") ownerId: Long,
        @Query("id") postId: Long
    ): ReturnStatusResponseDto

    @GET("fave.addPost?v=$VERSION")
    suspend fun addToFavourites(
        @Query("access_token") token: String,
        @Query("owner_id") ownerId: Long,
        @Query("id") postId: Long
    ): ReturnStatusResponseDto

    @GET("groups.getById?v=${VERSION}")
    suspend fun getGroupById(
        @Query("access_token") token: String,
        @Query("group_id") id: Long
    ): GetGroupResponse

    companion object {
        private const val VERSION = "5.199"
    }
}