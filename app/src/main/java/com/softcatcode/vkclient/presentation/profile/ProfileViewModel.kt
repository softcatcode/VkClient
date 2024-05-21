package com.softcatcode.vkclient.presentation.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcatcode.vkclient.domain.useCase.GetProfileInfoUseCase
import com.softcatcode.vkclient.domain.useCase.LoadProfileInfoUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val loadProfileUseCase: LoadProfileInfoUseCase,
    getProfileInfoUseCase: GetProfileInfoUseCase
): ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { throwable, _ ->
        Log.e(this::class.simpleName, throwable.toString())
    }

    val state = getProfileInfoUseCase().map {
        ProfileScreenState.Content(it) as ProfileScreenState
    }.onStart { emit(ProfileScreenState.Loading) }

    fun loadProfile() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            loadProfileUseCase()
        }
    }
}