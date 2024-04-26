package com.softcatcode.vkclient.presentation.home.comments

import androidx.lifecycle.ViewModel
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.useCase.GetCommentsUseCase
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommentsViewModel @Inject constructor(
    post: PostData,
    getCommentsUseCase: GetCommentsUseCase
): ViewModel() {

    val state = getCommentsUseCase(post).map {
        CommentsScreenState.Comments(it, post) as CommentsScreenState
    }

}