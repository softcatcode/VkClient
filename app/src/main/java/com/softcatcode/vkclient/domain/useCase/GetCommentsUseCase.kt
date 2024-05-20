package com.softcatcode.vkclient.domain.useCase

import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.interfaces.CommentsRepository
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val repository: CommentsRepository
) {
    operator fun invoke(post: PostData) = repository.getComments(post)
}