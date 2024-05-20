package com.softcatcode.vkclient.data.implementations

import android.app.Application
import com.softcatcode.vkclient.domain.entities.AuthState
import com.softcatcode.vkclient.domain.interfaces.AuthRepository
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(application: Application): AuthRepository {

    private val storage = VKPreferencesKeyValueStorage(application)
    private val authRequest = MutableSharedFlow<Unit>(replay = 1)
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private val token
        get() = VKAccessToken.restore(storage)

    override fun getAuthStateFlow() = flow {
        authRequest.emit(Unit)
        authRequest.collect {
            val currentToken = token
            val loggedIn = currentToken != null && currentToken.isValid
            val authState = if (loggedIn) AuthState.Authorized else AuthState.NotAuthorized
            emit(authState)
        }
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = AuthState.Initial
    )

    override suspend fun checkAuthResult() {
        authRequest.emit(Unit)
    }
}