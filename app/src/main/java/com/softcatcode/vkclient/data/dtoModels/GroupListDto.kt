package com.softcatcode.vkclient.data.dtoModels

import com.google.gson.annotations.SerializedName

data class GroupListDto(
    @SerializedName("groups") val items: List<GroupDto>
)
