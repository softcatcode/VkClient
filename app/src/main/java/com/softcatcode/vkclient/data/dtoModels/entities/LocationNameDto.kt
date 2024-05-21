package com.softcatcode.vkclient.data.dtoModels.entities

import com.google.gson.annotations.SerializedName

data class LocationNameDto(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val name: String
)