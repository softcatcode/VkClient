package com.softcatcode.vkclient.data.dtoModels.entities

import com.google.gson.annotations.SerializedName

data class FriendDto(
    @SerializedName("id") val id: Long,
    @SerializedName("city") val city: LocationNameDto,
    @SerializedName("online") val online: Int,
    @SerializedName("first_name") val name: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("photo_100") val photoUrl: String,
)
