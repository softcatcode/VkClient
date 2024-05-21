package com.softcatcode.vkclient.data.dtoModels.entities

import com.google.gson.annotations.SerializedName

data class PhotoUrlDto(
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String
)
