package com.softcatcode.vkclient.data.dtoModels.entities

import com.google.gson.annotations.SerializedName
import com.softcatcode.vkclient.data.dtoModels.lists.PhotoLinksDto

data class AttachmentDto(
    @SerializedName("photo") val photo: PhotoLinksDto?
)
