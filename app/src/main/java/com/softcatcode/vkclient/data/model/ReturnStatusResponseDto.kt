package com.softcatcode.vkclient.data.model

import com.google.gson.annotations.SerializedName

data class ReturnStatusResponseDto(
    @SerializedName("response") val status: Int
)
