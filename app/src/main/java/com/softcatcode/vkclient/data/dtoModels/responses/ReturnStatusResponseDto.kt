package com.softcatcode.vkclient.data.dtoModels.responses

import com.google.gson.annotations.SerializedName

data class ReturnStatusResponseDto(
    @SerializedName("response") val status: Int
)
