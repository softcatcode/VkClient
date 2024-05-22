package com.softcatcode.vkclient.data.dtoModels.responses

import com.google.gson.annotations.SerializedName
import com.softcatcode.vkclient.data.dtoModels.lists.FriendsDto

data class GetFriendsResponseDto(
    @SerializedName("response") val friends: FriendsDto
)
