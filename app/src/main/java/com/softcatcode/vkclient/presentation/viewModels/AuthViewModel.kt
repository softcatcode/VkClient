package com.softcatcode.vkclient.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softcatcode.vkclient.domain.entities.states.AuthState
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult

class AuthViewModel: ViewModel() {

    private val _state = MutableLiveData<AuthState>()
    val state: LiveData<AuthState> = _state

    init {
        _state.value = if (VK.isLoggedIn()) AuthState.Authorized else AuthState.NotAuthorized
    }

    fun performAuthResult(result: VKAuthenticationResult) {
        when (result) {
            is VKAuthenticationResult.Success -> _state.value = AuthState.Authorized
            is VKAuthenticationResult.Failed -> _state.value = AuthState.NotAuthorized
        }
    }

}