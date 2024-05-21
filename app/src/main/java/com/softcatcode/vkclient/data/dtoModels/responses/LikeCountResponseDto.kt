package com.softcatcode.vkclient.data.dtoModels.responses

import com.google.gson.annotations.SerializedName
import com.softcatcode.vkclient.data.dtoModels.statistics.LikeCountDto

data class LikeCountResponseDto(
    @SerializedName("response") val likeCount: LikeCountDto
)