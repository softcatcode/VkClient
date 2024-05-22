package com.softcatcode.vkclient.data.implementations

import com.softcatcode.vkclient.data.mapper.DtoMapper
import com.softcatcode.vkclient.data.network.ApiService
import com.softcatcode.vkclient.domain.entities.PostData
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import javax.inject.Inject
import kotlin.math.absoluteValue

class FavouritesRepository @Inject constructor(
    storage: VKPreferencesKeyValueStorage,
    private val apiService: ApiService,
    private val mapper: DtoMapper
): RecommendationsRepository(storage, apiService, mapper) {

    private var loadedCount = 0

    override suspend fun loadPosts() {
        val tokenValue = token()
        val response = apiService.loadFavourites(tokenValue, loadedCount, POST_PORTION_SIZE)
        val postList = response.content.items
        loadedCount += postList.size
        val groups = postList.map {
            val groupResponse = apiService.getGroupById(tokenValue, it.postDto.communityId.absoluteValue)
            groupResponse.content.items[0]
        }
        _posts.addAll(mapper.mapResponseToFavourites(response, groups))
        if (postList.size < POST_PORTION_SIZE)
            loadCompletedFlow.emit(Unit)
    }

    override suspend fun deletionRequest(post: PostData) {
        apiService.removeFromFavourites(token(), post.communityId, post.id)
    }

    companion object {
        private const val POST_PORTION_SIZE = 100
    }
}