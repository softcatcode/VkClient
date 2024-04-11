package com.softcatcode.vkclient.presentation.home.auth

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthenticationResult

class AuthViewModel(
    application: Application
): AndroidViewModel(application) {

    private val _state = MutableLiveData<AuthState>()
    val state: LiveData<AuthState> = _state

    init {
        val storage = VKPreferencesKeyValueStorage(application)
        val token = VKAccessToken.restore(storage)
        val loggedIn = token != null && token.isValid
        _state.value = if (loggedIn) AuthState.Authorized else AuthState.NotAuthorized
    }

    fun performAuthResult(result: VKAuthenticationResult) {
        when (result) {
            is VKAuthenticationResult.Success -> _state.value = AuthState.Authorized
            is VKAuthenticationResult.Failed -> _state.value = AuthState.NotAuthorized
        }
    }

}