package com.softcatcode.vkclient.domain.entities

data class Comment(
    val id: Long,
    val authorName: String,
    val date: String,
    val content: String,
    val authorAvatarUrl: String
)
