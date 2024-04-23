package com.softcatcode.vkclient.presentation.home.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.softcatcode.vkclient.data.implementations.NewsManager
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.presentation.extensions.mergeWith
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class NewsViewModel(application: Application): AndroidViewModel(application) {

    private val repository = NewsManager(getApplication())

    private val recommendationsFlow = repository.recommendations
    private val updatedStateFlow = MutableSharedFlow<NewsScreenState>()

    private val exceptionHandler = CoroutineExceptionHandler { throwable, _ ->
        Log.e("NewsViewModel", throwable.toString())
    }

    val state = recommendationsFlow
        .filter { it.isNotEmpty() }
        .map { NewsScreenState.Posts(postList = it) as NewsScreenState }
        .onStart { emit(NewsScreenState.Loading) }
        .mergeWith(updatedStateFlow)

    fun loadNextRecommendations() {
        viewModelScope.launch(exceptionHandler) {
            updatedStateFlow.emit(
                NewsScreenState.Posts(
                    postList = recommendationsFlow.value,
                    nextLoading = true
                )
            )
            repository.loadNext()
        }
    }

    fun removePost(id: Long) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            repository.ignorePost(id)
        }
    }

    fun changeLikeStatus(post: PostData) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            repository.changeLikeStatus(post)
        }
    }
}