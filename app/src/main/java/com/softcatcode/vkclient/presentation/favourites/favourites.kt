package com.softcatcode.vkclient.presentation.favourites

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.entities.StatisticsItem
import com.softcatcode.vkclient.presentation.extensions.getApplicationComponent
import com.softcatcode.vkclient.presentation.home.PostsContent
import com.softcatcode.vkclient.presentation.home.news.NewsViewModel

@Composable
fun FavouritesContent(
    paddingValues: PaddingValues,
    onCommentClickListener: (PostData, StatisticsItem) -> Unit
) {
    val component = getApplicationComponent().getFavouritesScreenComponentFactory().create()
    val viewModel: NewsViewModel = viewModel(factory = component.getViewModelFactory())
    PostsContent(paddingValues, onCommentClickListener, viewModel)
}