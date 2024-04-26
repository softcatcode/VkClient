package com.softcatcode.vkclient.domain.useCase

import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.interfaces.NewsManagerInterface
import javax.inject.Inject

class ChangeLikeStatusUseCase @Inject constructor(
    private val repository: NewsManagerInterface
) {
    suspend operator fun invoke(post: PostData) = repository.changeLikeStatus(post)
}