package com.softcatcode.vkclient.data.implementations

import android.app.Application
import com.softcatcode.vkclient.data.mapper.DtoMapper
import com.softcatcode.vkclient.data.network.ApiFactory
import com.softcatcode.vkclient.domain.entities.Comment
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.interfaces.CommentsRepository
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class CommentsRepositoryImpl @Inject constructor(application: Application): CommentsRepository {

    private val storage = VKPreferencesKeyValueStorage(application)
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private val mapper = DtoMapper()
    private val apiService = ApiFactory.apiService
    private val token
        get() = VKAccessToken.restore(storage)

    private fun token() = token?.accessToken ?: throw RuntimeException("Access token is null.")

    override fun getComments(post: PostData): StateFlow<List<Comment>> = flow {
        val response = apiService.loadComments(token(), post.communityId, post.id)
        emit(mapper.mapResponseToComments(response))
    }.retry {
        delay(RecommendationsRepository.RETRY_LOADING_TIMEOUT)
        true
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = listOf()
    )
}