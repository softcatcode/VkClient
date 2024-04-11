package com.softcatcode.vkclient.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.domain.entities.StatisticsType
import com.softcatcode.vkclient.presentation.home.news.NewsScreenState
import com.softcatcode.vkclient.presentation.home.news.PostScreen
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
fun HomeContent(
    paddingValues: PaddingValues,
    onCommentClickListener: (PostData) -> Unit
) {
    val viewModel: NewsViewModel = viewModel()
    val state = viewModel.state.observeAsState(HomeScreenState.Initial)

    when (val currentState = state.value) {

        is NewsScreenState.Posts -> PostScreen(
            viewModel,
            currentState.postList,
            paddingValues,
        ) { post, statItem ->
            if (statItem.type == StatisticsType.Comment)
                onCommentClickListener(post)
            else
                viewModel.updateStatistics(post.id, statItem.type)
        }

        is HomeScreenState.Initial -> {}
    }
}