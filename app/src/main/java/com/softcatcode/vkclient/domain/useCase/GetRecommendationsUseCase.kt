package com.softcatcode.vkclient.domain.useCase

import com.softcatcode.vkclient.domain.interfaces.NewsManagerInterface
import javax.inject.Inject

class GetRecommendationsUseCase @Inject constructor(
    private val repository: NewsManagerInterface
) {
    operator fun invoke() = repository.getRecommendations()
}