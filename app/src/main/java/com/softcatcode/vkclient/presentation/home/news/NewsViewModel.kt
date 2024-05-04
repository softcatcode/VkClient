package com.softcatcode.vkclient.presentation.home.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.useCase.ChangeLikeStatusUseCase
import com.softcatcode.vkclient.domain.useCase.GetRecommendationsUseCase
import com.softcatcode.vkclient.domain.useCase.IgnorePostUseCase
import com.softcatcode.vkclient.domain.useCase.LoadNextPostsUseCase
import com.softcatcode.vkclient.presentation.extensions.mergeWith
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

open class NewsViewModel @Inject constructor(
    private val getRecommendationsUseCase: GetRecommendationsUseCase,
    private val loadNextPostsUseCase: LoadNextPostsUseCase,
    private val ignorePostUseCase: IgnorePostUseCase,
    private val changeLikeStatusUseCase: ChangeLikeStatusUseCase
): ViewModel() {

    private val postFlow: StateFlow< List<PostData> >
        get() = invokeGetPostsUseCase()

    private val updatedStateFlow = MutableSharedFlow<NewsScreenState>()

    private val exceptionHandler = CoroutineExceptionHandler { throwable, _ ->
        Log.e("NewsViewModel", throwable.toString())
    }

    val state = postFlow
        .filter { it.isNotEmpty() }
        .map { NewsScreenState.Posts(postList = it) as NewsScreenState }
        .onStart { emit(NewsScreenState.Loading) }
        .mergeWith(updatedStateFlow)

    fun loadNext() {
        viewModelScope.launch(exceptionHandler) {
            updatedStateFlow.emit(
                NewsScreenState.Posts(
                    postList = postFlow.value,
                    nextLoading = true
                )
            )
            invokeLoadNextUseCase()
        }
    }

    fun removePost(id: Long) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            invokeRemovePostUseCase(id)
        }
    }

    fun changeLikeStatus(post: PostData) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            changeLikeStatusUseCase(post)
        }
    }

    protected open suspend fun invokeLoadNextUseCase() = loadNextPostsUseCase()
    protected open suspend fun invokeRemovePostUseCase(id: Long) = ignorePostUseCase(id)
    protected open fun invokeGetPostsUseCase() = getRecommendationsUseCase()
}