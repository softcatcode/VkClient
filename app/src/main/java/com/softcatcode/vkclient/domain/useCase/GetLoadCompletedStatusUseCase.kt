package com.softcatcode.vkclient.domain.useCase

import com.softcatcode.vkclient.domain.interfaces.PostManagerRepository
import javax.inject.Inject

class GetLoadCompletedStatusUseCase @Inject constructor(
    private val repository: PostManagerRepository
) {
    operator fun invoke() = repository.getLoadedStatusFlow()
}