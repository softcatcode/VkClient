package com.softcatcode.vkclient.domain.entities

import com.softcatcode.vkclient.R

data class Comment(
    val authorName: String = "Arthur",
    val date: String = "14:01",
    val content: String = "weird comment: lalala",
    val authorAvatarId: Int = R.drawable.comment_avatar,
    val id: Int
)
