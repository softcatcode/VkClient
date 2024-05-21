package com.softcatcode.vkclient.data.dtoModels.responses

import com.google.gson.annotations.SerializedName
import com.softcatcode.vkclient.data.dtoModels.lists.CommentsContentDto

data class CommentsResponseDto(
    @SerializedName("response") val content: CommentsContentDto
)
