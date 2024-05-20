package com.softcatcode.vkclient.domain.useCase

import com.softcatcode.vkclient.domain.interfaces.AuthRepository
import com.softcatcode.vkclient.domain.interfaces.PostManagerRepository
import javax.inject.Inject

class GetAuthStateFlowUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.getAuthStateFlow()
}