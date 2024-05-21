package com.softcatcode.vkclient.data.dtoModels.lists

import com.google.gson.annotations.SerializedName
import com.softcatcode.vkclient.data.dtoModels.entities.GroupDto

data class GroupListDto(
    @SerializedName("groups") val items: List<GroupDto>
)
