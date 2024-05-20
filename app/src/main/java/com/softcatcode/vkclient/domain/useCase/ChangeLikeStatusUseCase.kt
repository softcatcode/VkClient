package com.softcatcode.vkclient.domain.useCase

import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.interfaces.PostManagerRepository
import javax.inject.Inject

class ChangeLikeStatusUseCase @Inject constructor(
    private val repository: PostManagerRepository
) {
    suspend operator fun invoke(post: PostData) = repository.changeLikeStatus(post)
}