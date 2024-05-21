package com.softcatcode.vkclient.data.dtoModels.responses

import com.google.gson.annotations.SerializedName
import com.softcatcode.vkclient.data.dtoModels.lists.FavouritesContentDto

data class FavouritesResponseDto(
    @SerializedName("response") val content: FavouritesContentDto
)
