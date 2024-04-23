package com.softcatcode.vkclient.domain.entities

sealed class NewsFeedResult {
    data object Error: NewsFeedResult()

    data class Success(val postList: List<PostData>): NewsFeedResult()
}