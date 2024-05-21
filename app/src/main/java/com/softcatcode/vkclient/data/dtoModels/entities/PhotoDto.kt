package com.softcatcode.vkclient.data.dtoModels.entities

import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("date") val date: Long,
    @SerializedName("owner_id") val ownerId: Long,
    @SerializedName("sizes") val links: List<PhotoUrlDto>
)
