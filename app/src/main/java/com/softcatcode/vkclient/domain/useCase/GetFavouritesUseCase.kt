package com.softcatcode.vkclient.domain.useCase

import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.interfaces.NewsManagerInterface
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(
    private val repository: NewsManagerInterface
) {
    operator fun invoke(): StateFlow<List<PostData>> {
        return repository.getFavourites()
    }
}