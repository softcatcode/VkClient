package com.softcatcode.vkclient.domain.useCase

import com.softcatcode.vkclient.domain.interfaces.PostManagerRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: PostManagerRepository
) {
    operator fun invoke() = repository.getPosts()
}