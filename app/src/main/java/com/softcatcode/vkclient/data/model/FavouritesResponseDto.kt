package com.softcatcode.vkclient.data.model

import com.google.gson.annotations.SerializedName

data class FavouritesResponseDto(
    @SerializedName("response") val response: FavouritesContentDto
)
