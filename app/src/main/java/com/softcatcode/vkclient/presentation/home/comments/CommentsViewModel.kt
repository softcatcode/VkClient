package com.softcatcode.vkclient.presentation.home.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softcatcode.vkclient.domain.entities.Comment
import com.softcatcode.vkclient.domain.entities.PostData

class CommentsViewModel(
    post: PostData
): ViewModel() {

    private val _state = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val state: LiveData<CommentsScreenState> = _state

    init {
        loadComments(post)
    }

    private fun loadComments(post: PostData) {
        val comments = mutableListOf<Comment>().apply {
            repeat(10) {
                add(Comment(id = it))
            }
        }
        _state.value = CommentsScreenState.Comments(
            post = post,
            commentList = comments
        )
    }
}