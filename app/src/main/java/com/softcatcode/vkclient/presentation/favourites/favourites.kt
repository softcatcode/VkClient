package com.softcatcode.vkclient.presentation.favourites

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.entities.StatisticsItem
import com.softcatcode.vkclient.presentation.extensions.getApplicationComponent
import com.softcatcode.vkclient.presentation.home.PostsContent

@Composable
fun FavouritesContent(
    paddingValues: PaddingValues,
    onCommentClickListener: (PostData, StatisticsItem) -> Unit
) {
    val component = getApplicationComponent()
    val viewModel: FavouritesViewModel = viewModel(factory = component.getViewModelFactory())
    PostsContent(paddingValues, onCommentClickListener, viewModel)
}