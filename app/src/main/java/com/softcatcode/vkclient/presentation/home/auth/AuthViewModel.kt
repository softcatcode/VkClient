package com.softcatcode.vkclient.presentation.home.auth

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softcatcode.vkclient.data.implementations.NewsManager
import com.softcatcode.vkclient.data.network.ApiFactory
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthenticationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(
    application: Application
): AndroidViewModel(application) {

    private val repository = NewsManager(application)

    val state = repository.authState

    fun checkAuthResult() {
        viewModelScope.launch {
            repository.checkAuthResult()
        }
    }
}