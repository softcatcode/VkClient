package com.softcatcode.vkclient.presentation.home.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcatcode.vkclient.domain.useCase.CheckAuthResultUseCase
import com.softcatcode.vkclient.domain.useCase.GetAuthStateFlowUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    getAuthStateFlowUseCase: GetAuthStateFlowUseCase,
    private val checkAuthResultUseCase: CheckAuthResultUseCase
): ViewModel() {


    val state = getAuthStateFlowUseCase()

    fun checkAuthResult() {
        viewModelScope.launch {
            checkAuthResultUseCase()
        }
    }
}