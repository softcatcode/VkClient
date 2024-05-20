package com.softcatcode.vkclient.domain.useCase

import com.softcatcode.vkclient.domain.interfaces.PostManagerRepository
import javax.inject.Inject

class IgnorePostUseCase @Inject constructor(
    private val repository: PostManagerRepository
) {
    suspend operator fun invoke(id: Long) = repository.ignorePost(id)
}