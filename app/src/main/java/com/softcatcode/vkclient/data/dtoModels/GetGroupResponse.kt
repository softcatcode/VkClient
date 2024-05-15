package com.softcatcode.vkclient.data.dtoModels

import com.google.gson.annotations.SerializedName

data class GetGroupResponse(
    @SerializedName("response") val content: GroupListDto
)
