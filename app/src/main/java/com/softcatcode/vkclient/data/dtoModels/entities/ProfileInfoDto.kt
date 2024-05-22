package com.softcatcode.vkclient.data.dtoModels.entities

import com.google.gson.annotations.SerializedName

data class ProfileInfoDto(
    @SerializedName("id") val id: Long,
    @SerializedName("home_town") val city: String,
    @SerializedName("photo_200") val photoUrl: String,
    @SerializedName("bdate") val birthDate: String,
    @SerializedName("first_name") val name: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("country") val country: LocationNameDto
)
