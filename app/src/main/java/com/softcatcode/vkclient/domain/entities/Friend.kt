package com.softcatcode.vkclient.domain.entities

data class Friend(
    val id: Long,
    val name: String,
    val lastName: String,
    val avatarUrl: String,
    val online: Boolean
)