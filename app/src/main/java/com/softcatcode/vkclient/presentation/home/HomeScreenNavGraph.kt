package com.softcatcode.vkclient.presentation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.softcatcode.vkclient.domain.entities.PostData
import com.softcatcode.vkclient.presentation.navigation.Screen

fun NavGraphBuilder.homeScreenNavGraph(
    newsScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (PostData) -> Unit
) {
    navigation(
        route = Screen.Home.route,
        startDestination = Screen.News.route
    ) {
        composable(Screen.News.route) {
            newsScreenContent()
        }
        composable(
            route = Screen.Comments.route,
            arguments = listOf(
                navArgument(Screen.KEY_POST_JSON) {
                    type = PostData.NavigationType
                }
            )
        ) {
            val args = it.arguments ?: throw RuntimeException("Navigation arguments is null")
            val post = PostData.NavigationType[args, Screen.KEY_POST_JSON] ?:
                throw RuntimeException("No post in NavBackStackEntry.arguments.")
            commentsScreenContent(post)
        }
    }
}