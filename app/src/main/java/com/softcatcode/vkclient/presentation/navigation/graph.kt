package com.softcatcode.vkclient.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.presentation.home.homeScreenNavGraph

@Composable
fun AppNavGraph(
    navController: NavHostController,
    newsScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (PostData) -> Unit,
    favouriteScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        homeScreenNavGraph(
            newsScreenContent = newsScreenContent,
            commentsScreenContent = commentsScreenContent
        )
        composable(Screen.Favourite.route) {
            favouriteScreenContent()
        }
        composable(Screen.Profile.route) {
            profileScreenContent()
        }
    }
}