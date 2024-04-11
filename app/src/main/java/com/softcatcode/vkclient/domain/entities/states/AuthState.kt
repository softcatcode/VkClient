package com.softcatcode.vkclient.domain.entities.states

sealed class AuthState {
    data object Authorized: AuthState()
    data object NotAuthorized: AuthState()
    data object Initial: AuthState()
}