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

    private val _state = MutableLiveData<NewsScreenState>(NewsScreenState.Loading)
    val state: LiveData<NewsScreenState> = _state

    private val repository = NewsManager(getApplication())

    init {
        loadRecommendations()
    }

    fun removePost(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.ignorePost(id)
            withContext(Dispatchers.Main) {
                _state.value = NewsScreenState.Posts(repository.posts)
            }
        }
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