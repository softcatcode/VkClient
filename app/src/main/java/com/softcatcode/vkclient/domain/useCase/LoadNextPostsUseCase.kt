package com.softcatcode.vkclient.domain.useCase

import com.softcatcode.vkclient.domain.interfaces.NewsManagerInterface

class LoadNextPostsUseCase(
    private val repository: NewsManagerInterface
) {
    suspend operator fun invoke() = repository.loadNext()
}