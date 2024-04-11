package com.softcatcode.vkclient.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.softcatcode.vkclient.domain.entities.PostData

class NavigationState(
    val navController: NavHostController
) {
    val items = listOf(NavigationItem.Home, NavigationItem.Favourites, NavigationItem.Profile)

    fun navigate(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToComments(post: PostData) {
        navController.navigate(Screen.Comments.getPathWithArg(post))
    }

    companion object {
        @Composable
        fun rememberNavigationState(controller: NavHostController = rememberNavController()) = remember {
            NavigationState(controller)
        }
    }
}