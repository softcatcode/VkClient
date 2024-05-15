package com.softcatcode.vkclient.data.dtoModels

import com.google.gson.annotations.SerializedName

data class LikeCountDto(
    @SerializedName("likes") val count: Int
)
