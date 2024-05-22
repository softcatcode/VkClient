package com.softcatcode.vkclient.data.implementations

import android.util.Log
import com.softcatcode.vkclient.data.mapper.DtoMapper
import com.softcatcode.vkclient.data.network.ApiService
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.entities.StatisticsItem
import com.softcatcode.vkclient.domain.entities.StatisticsType
import com.softcatcode.vkclient.domain.interfaces.PostManagerRepository
import com.softcatcode.vkclient.presentation.extensions.mergeWith
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

open class RecommendationsRepository @Inject constructor(
    private val storage: VKPreferencesKeyValueStorage,
    private val apiService: ApiService,
    private val mapper: DtoMapper
): PostManagerRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private var nextFrom: String? = null

    init {
        Log.i("mumu", token())
    }

    private val token
        get() = VKAccessToken.restore(storage)

    protected val _posts = mutableListOf<PostData>()
    private val posts: List<PostData>
        get() = _posts.toList()

    private val postListRequestFlow = MutableSharedFlow<Unit>(replay = 1)
    private val updatedPostListFlow = MutableSharedFlow<List<PostData>>()
    private val postListFlow = flow {
        postListRequestFlow.emit(Unit)
        postListRequestFlow.collect {
            loadPosts()
            emit(posts)
        }
    }.retry(1) {
        delay(RETRY_LOADING_TIMEOUT)
        true
    }

    override fun getPosts(): StateFlow<List<PostData>> = postListFlow
        .mergeWith(updatedPostListFlow)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Lazily,
            initialValue = posts
        )

    protected open suspend fun loadPosts() {
        if (nextFrom == null && posts.isNotEmpty()) {
            loadCompletedFlow.emit(Unit)
            return
        }
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
        deletionRequest(post)
        updatedPostListFlow.emit(posts)
    }

    protected open suspend fun deletionRequest(post: PostData) {
        apiService.ignorePost(token(), post.communityId, post.id)
    }

    override suspend fun changeLikeStatus(post: PostData) {
        val newLikeCount = if (post.liked)
            apiService.dislike(token(), post.communityId, post.id).likeCount.count
        else
            apiService.like(token(), post.communityId, post.id).likeCount.count

        val newStatistics = post.statistics.toMutableList().apply {
            replaceAll {
                val count = if (it.type == StatisticsType.Like)
                    newLikeCount
                else
                    it.count
                it.copy(count = count)
            }
        }
        updatePostList(_posts, post.id, newStatistics, !post.liked)
        updatedPostListFlow.emit(posts)
    }

    protected val loadCompletedFlow = MutableSharedFlow<Unit>()
    override fun getLoadedStatusFlow() = loadCompletedFlow.asSharedFlow()

    protected fun token() = token?.accessToken ?: throw RuntimeException("Access token is null.")

    private fun updatePostList(list: MutableList<PostData>, postId: Long, newStatistics: List<StatisticsItem>, liked: Boolean) {
        list.replaceAll {
            if (it.id == postId)
                it.copy(statistics = newStatistics, liked = liked)
            else
                it
        }
    }

    companion object {
        internal const val RETRY_LOADING_TIMEOUT = 1000L
    }
}