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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CommentsViewModel(
    post: PostData,
    application: Application
): AndroidViewModel(application) {

    private val repository = NewsManager(getApplication())

    val state = repository.getComments(post).map {
        CommentsScreenState.Comments(it, post) as CommentsScreenState
    }

}