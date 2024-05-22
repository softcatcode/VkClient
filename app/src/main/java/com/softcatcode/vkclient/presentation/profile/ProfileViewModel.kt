package com.softcatcode.vkclient.presentation.profile

import androidx.lifecycle.ViewModel
import com.softcatcode.vkclient.domain.useCase.GetProfileInfoUseCase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    getProfileInfoUseCase: GetProfileInfoUseCase
): ViewModel() {

    val state = getProfileInfoUseCase().map {
        ProfileScreenState.Content(it) as ProfileScreenState
    }.onStart { emit(ProfileScreenState.Loading) }
}