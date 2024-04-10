package com.softcatcode.vkclient.presentation.viewModels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.presentation.viewModels.CommentsViewModel

class CommentsViewModelFactory(
    private val post: PostData
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(post) as T
    }
}