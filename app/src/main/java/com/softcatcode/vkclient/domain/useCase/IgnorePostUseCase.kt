package com.softcatcode.vkclient.domain.useCase

import com.softcatcode.vkclient.domain.interfaces.NewsManagerInterface
import javax.inject.Inject

class IgnorePostUseCase @Inject constructor(
    private val repository: NewsManagerInterface
) {
    suspend operator fun invoke(id: Long) = repository.ignorePost(id)
}