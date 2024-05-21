package com.softcatcode.vkclient.data.dtoModels.responses

import com.google.gson.annotations.SerializedName
import com.softcatcode.vkclient.data.dtoModels.entities.ProfileInfoDto

data class GetProfileInfoResponse(
    @SerializedName("response") val data: ProfileInfoDto
)
