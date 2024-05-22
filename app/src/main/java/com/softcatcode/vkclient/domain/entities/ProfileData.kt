package com.softcatcode.vkclient.domain.entities

data class ProfileData(
    val id: Long,
    val name: String,
    val age: Int,
    val lastName: String,
    val country: String?,
    val city: String?,
    val friends: List<Friend>,
    val avatarUrl: String,
    val photoLinks: List<String>
) {
    companion object {
        val INITIAL_DATA = ProfileData(
            id = 0,
            name = "",
            lastName = "",
            age = 0,
            country = null,
            city = null,
            friends = listOf(),
            avatarUrl = "",
            photoLinks = listOf()
        )
    }
}