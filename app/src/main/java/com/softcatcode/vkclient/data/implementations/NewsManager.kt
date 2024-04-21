package com.softcatcode.vkclient.data.implementations

import android.app.Application
import com.softcatcode.vkclient.data.mapper.NewsFeedMapper
import com.softcatcode.vkclient.data.network.ApiFactory
import com.softcatcode.vkclient.domain.entities.Comment
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.entities.StatisticsItem
import com.softcatcode.vkclient.domain.entities.StatisticsType
import com.softcatcode.vkclient.domain.interfaces.NewsManagerInterface
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken

class NewsManager(application: Application): NewsManagerInterface {

    private val storage = VKPreferencesKeyValueStorage(application)
    private val token = VKAccessToken.restore(storage)
    private val apiService = ApiFactory.apiService
    private val mapper = NewsFeedMapper()

    private val _posts = mutableListOf<PostData>()
    val posts: List<PostData>
        get() = _posts.toList()

    private var nextFrom: String? = null

    private fun token() = token?.accessToken ?: throw RuntimeException("Access token is null.")

    override suspend fun loadRecommendations(): List<PostData> {
        if (nextFrom == null && posts.isNotEmpty())
            return posts
        val response = nextFrom?.let {
            apiService.getRecommendations(token(), it)
        } ?: apiService.getRecommendations(token())
        nextFrom = response.newsFeedContent.nextFrom
        _posts.addAll(mapper.mapResponseToPosts(response))
        return posts
    }

    override suspend fun ignorePost(id: Long) {
        val post = _posts.find { it.id == id } ?: return
        _posts.remove(post)
        apiService.ignorePost(token(), post.communityId, post.id)
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
    }

    override suspend fun getComments(post: PostData): List<Comment> {
        val response = apiService.loadComments(token(), post.communityId, post.id)
        return mapper.mapResponseToComments(response)
    }
}