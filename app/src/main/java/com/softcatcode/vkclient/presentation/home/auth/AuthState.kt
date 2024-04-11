package com.softcatcode.vkclient.presentation.home.auth

sealed class AuthState {
    data object Authorized: AuthState()
    data object NotAuthorized: AuthState()
    data object Initial: AuthState()
}