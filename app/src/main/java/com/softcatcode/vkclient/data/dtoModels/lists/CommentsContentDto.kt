package com.softcatcode.vkclient.data.dtoModels.lists

import com.google.gson.annotations.SerializedName
import com.softcatcode.vkclient.data.dtoModels.entities.CommentDto
import com.softcatcode.vkclient.data.dtoModels.entities.ProfileDto

data class CommentsContentDto(
    @SerializedName("items") val comments: List<CommentDto>,
    @SerializedName("profiles") val profiles: List<ProfileDto>
)
