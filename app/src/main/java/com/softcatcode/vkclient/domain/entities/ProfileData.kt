package com.softcatcode.vkclient.domain.entities

data class ProfileData(
    val id: Long,
    val name: String,
    val lastName: String,
    val country: String?,
    val city: String?,
    val friends: List<Friend>,
    val photoLinks: List<String>
)