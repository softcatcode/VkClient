package com.softcatcode.vkclient.domain.useCase

import com.softcatcode.vkclient.domain.interfaces.NewsManagerInterface

class IgnorePostUseCase(
    private val repository: NewsManagerInterface
) {
    suspend operator fun invoke(id: Long) = repository.ignorePost(id)
}