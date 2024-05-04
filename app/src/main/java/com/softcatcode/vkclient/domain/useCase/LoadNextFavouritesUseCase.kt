package com.softcatcode.vkclient.domain.useCase

import com.softcatcode.vkclient.domain.interfaces.NewsManagerInterface
import javax.inject.Inject

class LoadNextFavouritesUseCase @Inject constructor(
    private val repository: NewsManagerInterface
) {
    suspend operator fun invoke() {
        repository.loadFavourites()
    }
}