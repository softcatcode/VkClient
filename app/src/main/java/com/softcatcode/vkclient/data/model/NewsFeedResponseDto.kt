package com.softcatcode.vkclient.data.model

import com.google.gson.annotations.SerializedName
import com.softcatcode.vkclient.data.model.NewsFeedContentDto

data class NewsFeedResponseDto(
    @SerializedName("response") val newsFeedContent: NewsFeedContentDto
)
