package com.sumin.vknewsclient.data.model

import com.google.gson.annotations.SerializedName
import com.softcatcode.vkclient.data.model.CommentDto
import com.softcatcode.vkclient.data.model.ProfileDto

data class CommentsContentDto(
    @SerializedName("items") val comments: List<CommentDto>,
    @SerializedName("profiles") val profiles: List<ProfileDto>
)
