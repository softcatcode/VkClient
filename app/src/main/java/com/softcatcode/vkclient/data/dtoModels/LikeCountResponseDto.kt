package com.softcatcode.vkclient.data.dtoModels

import com.google.gson.annotations.SerializedName

data class LikeCountResponseDto(
    @SerializedName("response") val likeCount: LikeCountDto
)