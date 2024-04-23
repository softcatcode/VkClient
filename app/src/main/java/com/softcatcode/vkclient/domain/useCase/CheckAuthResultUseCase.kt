package com.softcatcode.vkclient.domain.useCase

import com.softcatcode.vkclient.domain.interfaces.NewsManagerInterface

class CheckAuthResultUseCase(
    private val repository: NewsManagerInterface
) {
    suspend operator fun invoke() = repository.checkAuthResult()
}