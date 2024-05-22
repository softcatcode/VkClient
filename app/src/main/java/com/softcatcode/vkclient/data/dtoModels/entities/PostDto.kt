package com.softcatcode.vkclient.data.dtoModels.entities

import com.google.gson.annotations.SerializedName
import com.softcatcode.vkclient.data.dtoModels.statistics.LikesDto
import com.softcatcode.vkclient.data.dtoModels.statistics.RepostsDto
import com.softcatcode.vkclient.data.dtoModels.statistics.ViewsDto

data class PostDto(
    @SerializedName("id") val id: Long,
    @SerializedName("source_id") val communityId: Long,
    @SerializedName("text") val text: String,
    @SerializedName("date") val date: Long,
    @SerializedName("likes") val likes: LikesDto,
    @SerializedName("comments") val comments: CommentDto,
    @SerializedName("views") val views: ViewsDto?,
    @SerializedName("reposts") val reposts: RepostsDto,
    @SerializedName("attachments") val attachments: List<AttachmentDto>?
)