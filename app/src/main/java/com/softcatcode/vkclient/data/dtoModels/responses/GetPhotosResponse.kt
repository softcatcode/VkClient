package com.softcatcode.vkclient.data.dtoModels.responses

import com.google.gson.annotations.SerializedName
import com.softcatcode.vkclient.data.dtoModels.lists.PhotosDto

data class GetPhotosResponse(
    @SerializedName("response") val data: PhotosDto
)
