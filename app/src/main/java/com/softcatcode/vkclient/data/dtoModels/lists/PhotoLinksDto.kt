package com.softcatcode.vkclient.data.dtoModels.lists

import com.google.gson.annotations.SerializedName
import com.softcatcode.vkclient.data.dtoModels.entities.PhotoUrlDto

data class PhotoLinksDto(
    @SerializedName("sizes") val photoUrls: List<PhotoUrlDto>
)
