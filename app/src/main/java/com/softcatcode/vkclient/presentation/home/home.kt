package com.softcatcode.vkclient.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.entities.StatisticsItem
import com.softcatcode.vkclient.domain.entities.StatisticsType
import com.softcatcode.vkclient.presentation.extensions.ProgressBar
import com.softcatcode.vkclient.presentation.extensions.getApplicationComponent
import com.softcatcode.vkclient.presentation.home.news.NewsScreenState
import com.softcatcode.vkclient.presentation.home.news.PostList
import com.softcatcode.vkclient.presentation.home.news.NewsViewModel

@Composable
fun RowScope.BottomNavigationItem(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    labelResId: Int,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        modifier = modifier,
        selected = selected,
        onClick = onClick,
        label = {
                Text(text = stringResource(id = labelResId))
        },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        colors = NavigationBarItemColors(
            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
            unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
            disabledIconColor = Color.Transparent,
            disabledTextColor = Color.Transparent,
            selectedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun PostsContent(
    paddingValues: PaddingValues,
    onCommentClickListener: (PostData, StatisticsItem) -> Unit,
    viewModel: NewsViewModel
) {
    val state = viewModel.state.collectAsState(HomeScreenState.Initial)

    when (val currentState = state.value) {

        is NewsScreenState.Posts -> PostList(
            viewModel = viewModel,
            posts = currentState.postList,
            paddingValues = paddingValues,
            nextDataLoading = currentState.nextLoading
        ) { post, statItem ->
            when (statItem.type) {
                StatisticsType.Comment -> onCommentClickListener(post, statItem)
                StatisticsType.Like -> viewModel.changeLikeStatus(post)
                else -> {}
            }
        }

        is NewsScreenState.Loading -> {
            ProgressBar()
        }
    }
}

@Composable
fun NewsContent(
    paddingValues: PaddingValues,
    onCommentClickListener: (PostData, StatisticsItem) -> Unit
) {
    val component = getApplicationComponent().getRecommendationsScreenComponentFactory().create()
    val viewModel: NewsViewModel = viewModel(factory = component.getViewModelFactory())
    PostsContent(paddingValues, onCommentClickListener, viewModel)
}