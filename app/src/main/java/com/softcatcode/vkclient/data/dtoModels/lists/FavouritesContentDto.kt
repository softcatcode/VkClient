package com.softcatcode.vkclient.data.dtoModels.lists

import com.google.gson.annotations.SerializedName
import com.softcatcode.vkclient.data.dtoModels.entities.FavouriteItemDto

data class FavouritesContentDto(
    @SerializedName("count") val count: Int,
    @SerializedName("items") val items: List<FavouriteItemDto>
)