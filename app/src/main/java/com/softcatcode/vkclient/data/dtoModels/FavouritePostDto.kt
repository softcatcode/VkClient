package com.softcatcode.vkclient.data.dtoModels

import com.google.gson.annotations.SerializedName

data class FavouritePostDto(
    @SerializedName("id") val id: Long,
    @SerializedName("from_id") val communityId: Long,
    @SerializedName("text") val text: String,
    @SerializedName("date") val date: Long,
    @SerializedName("likes") val likes: LikesDto,
    @SerializedName("comments") val comments: CommentDto,
    @SerializedName("views") val views: ViewsDto?,
    @SerializedName("reposts") val reposts: RepostsDto,
    @SerializedName("attachments") val attachments: List<AttachmentDto>?
)