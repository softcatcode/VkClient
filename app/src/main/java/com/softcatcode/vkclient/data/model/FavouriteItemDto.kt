package com.softcatcode.vkclient.data.model

import com.google.gson.annotations.SerializedName

data class FavouriteItemDto(
    @SerializedName("added_date") val addTime: Long,
    @SerializedName("seen") val isSeen: Boolean,
    @SerializedName("post") val postDto: PostDto
)
