package com.softcatcode.vkclient.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.softcatcode.vkclient.domain.entities.NavigationItem
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
}