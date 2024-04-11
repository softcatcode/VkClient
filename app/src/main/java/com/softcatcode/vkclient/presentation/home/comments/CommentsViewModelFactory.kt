package com.softcatcode.vkclient.presentation.home.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.softcatcode.vkclient.domain.entities.PostData

class CommentsViewModelFactory(
    private val post: PostData
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(post) as T
    }
}