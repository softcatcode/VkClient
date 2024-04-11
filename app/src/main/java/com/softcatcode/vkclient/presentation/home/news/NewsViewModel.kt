package com.softcatcode.vkclient.presentation.home.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softcatcode.vkclient.domain.entities.Comment
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.entities.StatisticsItem
import com.softcatcode.vkclient.domain.entities.StatisticsType

class NewsViewModel: ViewModel() {

    private val _state = MutableLiveData<NewsScreenState>()
    val state: LiveData<NewsScreenState> = _state

    private var posts = mutableListOf<PostData>()
    private var comments = mutableListOf<Comment>()

    init {
        var id = 1
        repeat(100) {
            posts.add(PostData(id = id))
            comments.add(Comment(id = id))
            ++id
        }
        _state.value = NewsScreenState.Posts(posts)
    }

    private fun updateStatList(list: List<StatisticsItem>, type: StatisticsType) = list
        .toMutableList()
        .apply {
            replaceAll {
                if (it.type == type)
                    it.copy(count = it.count + 1)
                else
                    it
            }
        }

    fun updateStatistics(postId: Int, type: StatisticsType) {
        val currentState = state.value
        if (currentState !is NewsScreenState.Posts)
            return

        val postList = currentState.postList.toMutableList()
        postList.replaceAll {
            if (it.id == postId)
                it.copy(statistics = updateStatList(it.statistics, type))
            else
                it
        }
        _state.value = NewsScreenState.Posts(postList)
    }

    fun removePost(id: Int) {
        val currentState = state.value
        if (currentState !is NewsScreenState.Posts)
            return

        val postList = currentState.postList.toMutableList().apply {
            removeAll { it.id == id }
        }
        _state.value = NewsScreenState.Posts(postList)
    }
}