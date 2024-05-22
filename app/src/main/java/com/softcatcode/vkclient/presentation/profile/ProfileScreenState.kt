package com.softcatcode.vkclient.presentation.profile

import com.softcatcode.vkclient.domain.entities.ProfileData

sealed class ProfileScreenState {
    data object Initial: ProfileScreenState()

    data object Loading: ProfileScreenState()

    data class Content(val data: ProfileData): ProfileScreenState()
}