package com.softcatcode.vkclient.domain.interfaces

import com.softcatcode.vkclient.domain.entities.ProfileData
import kotlinx.coroutines.flow.StateFlow

interface ProfileRepository {

    suspend fun loadProfile()

    fun getProfileDataFlow(): StateFlow<ProfileData>
}