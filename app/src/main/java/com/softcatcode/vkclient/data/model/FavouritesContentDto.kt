package com.softcatcode.vkclient.data.model

import com.google.gson.annotations.SerializedName

data class FavouritesContentDto(
    @SerializedName("count") val count: Int,
    @SerializedName("items") val items: List<FavouriteItemDto>
)