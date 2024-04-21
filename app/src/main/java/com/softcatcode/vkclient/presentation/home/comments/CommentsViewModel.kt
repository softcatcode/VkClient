package com.softcatcode.vkclient.presentation.home.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softcatcode.vkclient.data.implementations.NewsManager
import com.softcatcode.vkclient.domain.entities.Comment
import com.softcatcode.vkclient.domain.entities.PostData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CommentsViewModel(
    post: PostData,
    application: Application
): AndroidViewModel(application) {

    private val _state = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val state: LiveData<CommentsScreenState> = _state

    private val repository = NewsManager(getApplication())

    init {
        loadComments(post)
    }

    private fun loadComments(post: PostData) {
        viewModelScope.launch(Dispatchers.IO) {
            val comments = repository.getComments(post)
            withContext(Dispatchers.Main) {
                _state.value = CommentsScreenState.Comments(
                    post = post,
                    commentList = comments
                )
            }
        }
    }
}