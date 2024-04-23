package com.softcatcode.vkclient.data.implementations

import android.app.Application
import com.softcatcode.vkclient.data.mapper.NewsFeedMapper
import com.softcatcode.vkclient.data.network.ApiFactory
import com.softcatcode.vkclient.domain.entities.Comment
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.entities.StatisticsItem
import com.softcatcode.vkclient.domain.entities.StatisticsType
import com.softcatcode.vkclient.domain.interfaces.NewsManagerInterface
import com.softcatcode.vkclient.presentation.extensions.mergeWith
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class NewsManager(application: Application): NewsManagerInterface {

    private val storage = VKPreferencesKeyValueStorage(application)
    private val token = VKAccessToken.restore(storage)
    private val apiService = ApiFactory.apiService
    private val mapper = NewsFeedMapper()

    private val _posts = mutableListOf<PostData>()
    private val posts: List<PostData>
        get() = _posts.toList()

    private var nextFrom: String? = null


    private val postListRequestFlow = MutableSharedFlow<Unit>(replay = 1)
    private val updatedPostListFlow = MutableSharedFlow<List<PostData>>()
    private val postListFlow: Flow<List<PostData>> = flow {
        postListRequestFlow.emit(Unit)
        postListRequestFlow.collect {
            loadRecommendations()
            emit(posts)
        }
    }

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    val recommendations: StateFlow<List<PostData>> = postListFlow
        .mergeWith(updatedPostListFlow)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Lazily,
            initialValue = posts
        )

    private fun token() = token?.accessToken ?: throw RuntimeException("Access token is null.")

    private suspend fun loadRecommendations() {
        if (nextFrom == null && posts.isNotEmpty())
            return
        val response = nextFrom?.let {
            apiService.getRecommendations(token(), it)
        } ?: apiService.getRecommendations(token())
        nextFrom = response.newsFeedContent.nextFrom
        _posts.addAll(mapper.mapResponseToPosts(response))
    }

    override suspend fun loadNext() {
        postListRequestFlow.emit(Unit)
    }

    override suspend fun ignorePost(id: Long) {
        val post = posts.find { it.id == id } ?: return
        _posts.remove(post)
        apiService.ignorePost(token(), post.communityId, post.id)
        updatedPostListFlow.emit(posts)
    }

    override suspend fun changeLikeStatus(post: PostData) {
        val newLikeCount = if (post.liked)
            apiService.dislike(token(), post.communityId, post.id).likeCount.count
        else
            apiService.like(token(), post.communityId, post.id).likeCount.count
        val newStatistics = post.statistics.toMutableList().apply {
            removeIf { it.type == StatisticsType.Like }
            add(StatisticsItem(StatisticsType.Like, newLikeCount))
        }
        val index = posts.indexOfFirst { post.id == it.id }
        _posts[index] = post.copy(statistics = newStatistics, liked = !post.liked)
        updatedPostListFlow.emit(posts)
    }

    override suspend fun getComments(post: PostData): List<Comment> {
        val response = apiService.loadComments(token(), post.communityId, post.id)
        return mapper.mapResponseToComments(response)
    }
}