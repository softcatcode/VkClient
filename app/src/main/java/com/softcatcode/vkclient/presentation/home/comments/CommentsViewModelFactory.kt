package com.softcatcode.vkclient.presentation.home.comments

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.softcatcode.vkclient.domain.entities.PostData

class CommentsViewModelFactory(
    private val post: PostData,
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(post, application) as T
    }
}