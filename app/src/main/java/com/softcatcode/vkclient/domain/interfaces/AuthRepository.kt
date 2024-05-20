package com.softcatcode.vkclient.domain.interfaces

import com.softcatcode.vkclient.domain.entities.AuthState
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {

    fun getAuthStateFlow(): StateFlow<AuthState>

    suspend fun checkAuthResult()
}