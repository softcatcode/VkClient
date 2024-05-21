package com.softcatcode.vkclient.data.dtoModels.responses

import com.google.gson.annotations.SerializedName
import com.softcatcode.vkclient.data.dtoModels.lists.NewsFeedContentDto

data class NewsFeedResponseDto(
    @SerializedName("response") val newsFeedContent: NewsFeedContentDto
)
