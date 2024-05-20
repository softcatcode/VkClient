package com.softcatcode.vkclient.domain.useCase

import com.softcatcode.vkclient.domain.interfaces.AuthRepository
import com.softcatcode.vkclient.domain.interfaces.PostManagerRepository
import javax.inject.Inject

class CheckAuthResultUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.checkAuthResult()
}