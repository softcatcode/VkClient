package com.softcatcode.vkclient.presentation.home.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.softcatcode.vkclient.data.implementations.NewsManager
import com.softcatcode.vkclient.domain.entities.PostData
import kotlinx.coroutines.flow.map

class CommentsViewModel(
    post: PostData,
    application: Application
): AndroidViewModel(application) {

    private val repository = NewsManager(getApplication())

    val state = repository.getComments(post).map {
        CommentsScreenState.Comments(it, post) as CommentsScreenState
    }

}