package com.softcatcode.vkclient.presentation.home.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softcatcode.vkclient.data.implementations.NewsManager
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.entities.StatisticsItem
import com.softcatcode.vkclient.domain.entities.StatisticsType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(application: Application): AndroidViewModel(application) {

    private val _state = MutableLiveData<NewsScreenState>(NewsScreenState.Initial)
    val state: LiveData<NewsScreenState> = _state

    private val repository = NewsManager(getApplication())

    init {
        loadRecommendations()
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

    fun updateStatistics(postId: Long, type: StatisticsType) {
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

    fun removePost(id: Long) {
        val currentState = state.value
        if (currentState !is NewsScreenState.Posts)
            return

        val postList = currentState.postList.toMutableList().apply {
            removeAll { it.id == id }
        }
        _state.value = NewsScreenState.Posts(postList)
    }

    fun loadRecommendations() {
        viewModelScope.launch(Dispatchers.IO) {
            val postList = repository.loadRecommendations()
            withContext(Dispatchers.Main) {
                _state.value = NewsScreenState.Posts(postList)
            }
        }
    }

    fun loadNextRecommendations() {
        _state.value = NewsScreenState.Posts(
            postList = repository.posts,
            nextLoading = true
        )
        loadRecommendations()
    }

    fun changeLikeStatus(post: PostData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeLikeStatus(post)
            withContext(Dispatchers.Main) {
                _state.value = NewsScreenState.Posts(repository.posts)
            }
        }
    }
}