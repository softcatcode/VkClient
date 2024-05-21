package com.softcatcode.vkclient.data.network

import com.softcatcode.vkclient.data.dtoModels.responses.FavouritesResponseDto
import com.softcatcode.vkclient.data.dtoModels.responses.GetGroupResponse
import com.softcatcode.vkclient.data.dtoModels.responses.LikeCountResponseDto
import com.softcatcode.vkclient.data.dtoModels.responses.NewsFeedResponseDto
import com.softcatcode.vkclient.data.dtoModels.responses.ReturnStatusResponseDto
import com.softcatcode.vkclient.data.dtoModels.responses.CommentsResponseDto
import com.softcatcode.vkclient.data.dtoModels.responses.GetFriendsResponseDto
import com.softcatcode.vkclient.data.dtoModels.responses.GetPhotosResponse
import com.softcatcode.vkclient.data.dtoModels.responses.GetProfileInfoResponse
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

    @GET("friends.get?v=${VERSION}&fields=photo_100,city,online")
    suspend fun getFriends(
        @Query("access_token") token: String
    ): GetFriendsResponseDto

    @GET("photos.getAll?v=${VERSION}")
    suspend fun getPhotos(
        @Query("access_token") token: String,
        @Query("owner_id") profileId: String
    ): GetPhotosResponse

    @GET("account.getProfileInfo?v-${VERSION}")
    suspend fun getProfileInfo(
        @Query("access_token") token: String
    ): GetProfileInfoResponse

    companion object {
        private const val VERSION = "5.199"
    }
}