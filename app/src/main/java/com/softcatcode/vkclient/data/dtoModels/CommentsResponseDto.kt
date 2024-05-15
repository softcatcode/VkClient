package com.softcatcode.vkclient.data.dtoModels

import com.google.gson.annotations.SerializedName
import com.sumin.vknewsclient.data.model.CommentsContentDto

data class CommentsResponseDto(
    @SerializedName("response") val content: CommentsContentDto
)
