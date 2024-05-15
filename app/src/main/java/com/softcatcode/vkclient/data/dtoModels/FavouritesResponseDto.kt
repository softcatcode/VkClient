package com.softcatcode.vkclient.data.dtoModels

import com.google.gson.annotations.SerializedName

data class FavouritesResponseDto(
    @SerializedName("response") val content: FavouritesContentDto
)
