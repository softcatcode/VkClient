package com.softcatcode.vkclient.data.model

import com.google.gson.annotations.SerializedName

data class LikeCountResponseDto(
    @SerializedName("response") val likeCount: LikeCountDto
)