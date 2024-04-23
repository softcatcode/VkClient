package com.softcatcode.vkclient.presentation.home.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.softcatcode.vkclient.data.implementations.NewsManager
import com.softcatcode.vkclient.domain.useCase.GetAuthStateFlowUseCase
import kotlinx.coroutines.launch

class AuthViewModel(
    application: Application
): AndroidViewModel(application) {

    private val repository = NewsManager(application)
    private val getAuthStateFlowUseCase = GetAuthStateFlowUseCase(repository)

    val state = getAuthStateFlowUseCase()

    fun checkAuthResult() {
        viewModelScope.launch {
            repository.checkAuthResult()
        }
    }
}