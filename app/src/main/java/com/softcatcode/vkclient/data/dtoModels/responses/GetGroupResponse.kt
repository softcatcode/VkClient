package com.softcatcode.vkclient.data.dtoModels.responses

import com.google.gson.annotations.SerializedName
import com.softcatcode.vkclient.data.dtoModels.lists.GroupListDto

data class GetGroupResponse(
    @SerializedName("response") val content: GroupListDto
)
