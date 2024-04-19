package com.softcatcode.vkclient.data.implementations

import android.app.Application
import com.softcatcode.vkclient.data.mapper.NewsFeedMapper
import com.softcatcode.vkclient.data.network.ApiFactory
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

    private fun token() = token?.accessToken ?: throw RuntimeException("Access token is null.")

//    override suspend fun loadRecommendations() = _posts.apply {
//        val response = apiService.getRecommendations(token())
//        addAll(mapper.mapResponseToPosts(response))
//    }

    override suspend fun loadRecommendations(): List<PostData> {
        val response = apiService.getRecommendations(token())
        _posts.addAll(mapper.mapResponseToPosts(response))
        return posts
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
}