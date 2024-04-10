package com.softcatcode.vkclient.presentation.ui.compose

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.softcatcode.vkclient.navigation.AppNavGraph
import com.softcatcode.vkclient.navigation.NavigationState
import com.softcatcode.vkclient.navigation.rememberNavigationState

@Composable
fun VkBottomNavigationBar(navState: NavigationState) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        navState.items.forEach { item ->
            val backStackEntry by navState.navController.currentBackStackEntryAsState()
            val selected = backStackEntry?.destination?.hierarchy?.any {
                it.route == item.screen.route
            } ?: false

            BottomNavigationItem(
                selected = selected,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                onClick = {
                    if (!selected)
                        navState.navigate(item.screen.route)
                },
                labelResId = item.titleResourceId,
                icon = item.icon,
            )

        }
    }
}

@Composable
fun VkMainScreen() {
    val navState = rememberNavigationState()

    Scaffold(
        bottomBar = { VkBottomNavigationBar(navState) }
    ) {
        AppNavGraph(
            navController = navState.navController,
            newsScreenContent = {
                HomeContent(paddingValues = it) { post ->
                    navState.navigateToComments(post)
                }
            },
            commentsScreenContent = { post ->
                CommentScreen(post) {
                    navState.navController.popBackStack()
                }
            },
            favouriteScreenContent = {  },
            profileScreenContent = {  }
        )
    }
}