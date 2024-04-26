package com.softcatcode.vkclient.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.entities.StatisticsItem
import com.softcatcode.vkclient.domain.entities.StatisticsType
import com.softcatcode.vkclient.presentation.extensions.getApplicationComponent
import com.softcatcode.vkclient.presentation.home.news.NewsScreenState
import com.softcatcode.vkclient.presentation.home.news.PostScreen
import com.softcatcode.vkclient.presentation.home.news.NewsViewModel
import com.softcatcode.vkclient.presentation.ui.theme.DarkBlue

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
fun HomeContent(
    paddingValues: PaddingValues,
    onCommentClickListener: (PostData, StatisticsItem) -> Unit
) {
    val component = getApplicationComponent()
    val viewModel: NewsViewModel = viewModel(factory = component.getViewModelFactory())
    val state = viewModel.state.collectAsState(HomeScreenState.Initial)

    when (val currentState = state.value) {

        is NewsScreenState.Posts -> PostScreen(
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
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = DarkBlue)
            }
        }
    }
}