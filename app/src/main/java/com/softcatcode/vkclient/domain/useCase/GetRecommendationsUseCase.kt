package com.softcatcode.vkclient.domain.useCase

import com.softcatcode.vkclient.domain.interfaces.NewsManagerInterface

class GetRecommendationsUseCase(
    private val repository: NewsManagerInterface
) {
    operator fun invoke() = repository.getRecommendations()
}